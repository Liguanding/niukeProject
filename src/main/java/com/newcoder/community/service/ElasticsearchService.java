package com.newcoder.community.service;

import com.newcoder.community.dao.elasticsearch.DiscussPostRepository;
import com.newcoder.community.entity.DiscussPost;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ElasticsearchService {

    @Autowired
    private DiscussPostRepository discussPostRepository;

    @Autowired
    private ElasticsearchRestTemplate restTemplate;

    public void saveDiscussPost(DiscussPost post){
        discussPostRepository.save(post);
    }

    public void deleteDiscussPost(int id){
        discussPostRepository.deleteById(id);
    }

    public Page<DiscussPost> searchDiscussPost(String keyWord,int current,int limit){
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.multiMatchQuery(keyWord,"title","content"))
                .withSort(SortBuilders.fieldSort("type").order(SortOrder.DESC))
                .withSort(SortBuilders.fieldSort("score").order(SortOrder.DESC))
                .withSort(SortBuilders.fieldSort("createTime").order(SortOrder.DESC))
                .withPageable(PageRequest.of(current,10))
                .withHighlightFields(
                        new HighlightBuilder.Field("title").preTags("<em>").postTags("</em>"),
                        new HighlightBuilder.Field("content").preTags("<em>").postTags("</em>")
                ).build();

        SearchHits<DiscussPost> searchHits = restTemplate.search(searchQuery, DiscussPost.class);
        List<DiscussPost> searchProductList = searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());

        List<DiscussPost> list = new ArrayList<>();
        for(SearchHit post : searchHits){
            List<String> titles = post.getHighlightField("title");
            List<String> contents = post.getHighlightField("content");

            DiscussPost discussPost = (DiscussPost) post.getContent();
            if(titles != null && !titles.isEmpty()){
                discussPost.setTitle(titles.get(0));
            }

            if(contents != null && !contents.isEmpty()){
                discussPost.setContent(contents.get(0));
            }
            list.add(discussPost);
        }

        Page<DiscussPost> page = new PageImpl<DiscussPost>(list,searchQuery.getPageable(),searchHits.getTotalHits());

        return page;

    }


}
