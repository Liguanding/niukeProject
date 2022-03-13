package com.newcoder.community;

import com.newcoder.community.dao.DiscussPostMapper;
import com.newcoder.community.dao.elasticsearch.DiscussPostRepository;
import com.newcoder.community.entity.DiscussPost;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.query.QuerySearchResult;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.annotations.HighlightParameters;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class ElasticsearchTest {
    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private DiscussPostRepository discussPostRepository;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Test
    public void testInsert(){

        IndexOperations ops = elasticsearchRestTemplate.indexOps(DiscussPost.class);
        boolean exists = ops.exists();
        System.out.println(exists);

        elasticsearchRestTemplate.save(discussPostMapper.selectDiscussPostById(241));
        elasticsearchRestTemplate.save(discussPostMapper.selectDiscussPostById(242));
        elasticsearchRestTemplate.save(discussPostMapper.selectDiscussPostById(243));
    }


    @Test
    public void testInsertList(){

//        elasticsearchRestTemplate.save(discussPostMapper.selectDiscussPosts(101,0,100));
//        elasticsearchRestTemplate.save(discussPostMapper.selectDiscussPosts(102,0,100));
//        elasticsearchRestTemplate.save(discussPostMapper.selectDiscussPosts(103,0,100));
//        elasticsearchRestTemplate.save(discussPostMapper.selectDiscussPosts(111,0,100));
//        elasticsearchRestTemplate.save(discussPostMapper.selectDiscussPosts(112,0,100));
//        elasticsearchRestTemplate.save(discussPostMapper.selectDiscussPosts(131,0,100));
//        elasticsearchRestTemplate.save(discussPostMapper.selectDiscussPosts(132,0,100));
    }

    @Test
    public void testUpdate(){

        DiscussPost post = discussPostMapper.selectDiscussPostById(111);
        post.setContent("我是新人,怎么灌水");
        discussPostRepository.save(post);
//        elasticsearchRestTemplate.save(post);
    }

    @Test
    public void testSearchByRepository(){


        //https://www.coder.work/article/7659882
        //这边问题非常多 ElasticsearchRepository 中的所有方法均已弃用

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.multiMatchQuery("互联网寒冬","title","content"))
                .withSort(SortBuilders.fieldSort("type").order(SortOrder.DESC))
                .withSort(SortBuilders.fieldSort("score").order(SortOrder.DESC))
                .withSort(SortBuilders.fieldSort("createTime").order(SortOrder.DESC))
                .withPageable(PageRequest.of(0,10))
                .withHighlightFields(
                        new HighlightBuilder.Field("title").preTags("<em>").postTags("</em>"),
                        new HighlightBuilder.Field("content").preTags("<em>").postTags("</em>")
                ).build();
        //https://www.jianshu.com/p/6b6ae4b2d7ef

        //SearchHits 如何转换为Page https://cloud.tencent.com/developer/ask/sof/1090704
//
//        第一种方法
//        SearchPage<YourClass> page = SearchHitSupport.searchPageFor(searchHits, query.getPageable());
//        return (Page)SearchHitSupport.unwrapSearchHits(page);
//
//         第二种方法
//        public SearchPage<Metadata> search(Request request){
//              Query query = this.queryBuilder(request);
//              query.setPageable(PageRequest.of(PageNumber,PageSize));
//              SearchHits<Metadata> hits = operations.search(query, Metadata.class);
//              return SearchHitSupport.searchPageFor(hits, query.getPageable());
//        }

/*        // ElasticsearchRepository实现复杂搜索  已经过时了
        return productRepository.search(searchQuery)
// ElasticsearchRestTemplate实现复杂搜索
        SearchHits<EsProduct> searchHits = elasticsearchRestTemplate.search(searchQuery, EsProduct.class);
        if(searchHits.getTotalHits()<=0){
            return new PageImpl<>(null,pageable,0);
        }
        List<EsProduct> searchProductList = searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
        return new PageImpl<>(searchProductList,pageable,searchHits.getTotalHits());*/


        SearchHits<DiscussPost> searchHits = elasticsearchRestTemplate.search(searchQuery, DiscussPost.class);
        if(searchHits.getTotalHits() <= 0){
            new PageImpl<>(null,searchQuery.getPageable(),0);
        }
//        SearchPage<DiscussPost> page =  SearchHitSupport.searchPageFor(searchHits, searchQuery.getPageable());

        Page<DiscussPost> page = discussPostRepository.findAll(searchQuery.getPageable());

        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
        for(DiscussPost post : page){

            System.out.println(post);
        }

    }

    @Test
    public void searchAndHighLight(){


        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.multiMatchQuery("互联网寒冬","title","content"))
                .withSort(SortBuilders.fieldSort("type").order(SortOrder.DESC))
                .withSort(SortBuilders.fieldSort("score").order(SortOrder.DESC))
                .withSort(SortBuilders.fieldSort("createTime").order(SortOrder.DESC))
                .withPageable(PageRequest.of(0,10))
                .withHighlightFields(
                        new HighlightBuilder.Field("title").preTags("<em>").postTags("</em>"),
                        new HighlightBuilder.Field("content").preTags("<em>").postTags("</em>")
                ).build();

        SearchHits<DiscussPost> searchHits = elasticsearchRestTemplate.search(searchQuery, DiscussPost.class);
        List<SearchHit<DiscussPost>> searchHits1 = searchHits.getSearchHits();

        for(SearchHit post : searchHits1){
            List<String> title = post.getHighlightField("title");
            System.out.println(post.getContent());
        }

    }




}
