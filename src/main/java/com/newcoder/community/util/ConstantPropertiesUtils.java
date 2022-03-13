package com.newcoder.community.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantPropertiesUtils implements InitializingBean {

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.bucket.header.name}")
    private String headerBucketName;

    @Value("${aliyun.oss.bucket.image.name}")
    private String iamgeBucketName;

    @Value("${aliyun.oss.bucket.header.url}")
    private String headerBucketUrl;

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    //定义公开静态常量
    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String HEADER_BUCKET_NAME;
    public static String IMAGE_BUCKET_NAME;
    public static String HEADERBUCKETURL;


    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = endpoint;
        ACCESS_KEY_ID = accessKeyId;
        ACCESS_KEY_SECRET = accessKeySecret;
        HEADER_BUCKET_NAME = headerBucketName;
        IMAGE_BUCKET_NAME = iamgeBucketName;
        HEADERBUCKETURL = headerBucketUrl;
    }
}
