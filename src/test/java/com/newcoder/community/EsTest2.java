package com.newcoder.community;

import com.newcoder.community.entity.DiscussPost;
import com.newcoder.community.service.ElasticsearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class EsTest2 {

    @Autowired
    ElasticsearchService esService;

    @Test
    public void testPage() {
        Page<DiscussPost> page = esService.searchDiscussPost("互联网", 0, 10);
        System.out.println(page.getSize());
        System.out.println(page.getTotalPages());
        System.out.println(page.getTotalElements());
        for (DiscussPost post : page) {
            System.out.println(post);
        }
    }


}
