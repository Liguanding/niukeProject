package com.newcoder.community;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class ThreadPoolTests {

    private static final Logger  logger = LoggerFactory.getLogger(ThreadPoolTests.class);

    //JDK普通线程池
    private ExecutorService executorService = Executors.newFixedThreadPool(5);

    //JDK可执行定时任务的线程池
    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

    //spring普通线程池
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    //spring定时任务线程池
    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    private void sleep(long m){
        try{
            Thread.sleep(m);
        }catch (InterruptedException e){
            e.printStackTrace();;
        }
    }
    //1.jdk普通线程池
    @Test
    public void testExecutorService(){
        Runnable task = new Runnable() {
            @Override
            public void run() {
                logger.debug("Hello ExecutorService");
            }
        };

        for(int i = 0;i < 10;++i){
            executorService.submit(task);
        }
        sleep(10);
    }

    //2.jdk定时任务线程池
//    @Test
//    public void testScheduledExecutorService(){
//        Runnable task = new Runnable() {
//            @Override
//            public void run() {
//                logger.debug("Hello ScheduledExecutorService");
//            }
//        };
//        scheduledExecutorService.scheduleAtFixedRate(task,1000,1000, TimeUnit.MILLISECONDS);
//
//        sleep(30000);
//    }

    //3.Spring普通线程池
    @Test
    public void testThreadPoolTaskExecutor(){
        Runnable task = new Runnable() {
            @Override
            public void run() {
                logger.debug("Hello ExecutorService");
            }
        };

        for(int i = 0;i < 10;++i){
            taskExecutor.submit(task);
        }
        sleep(10);
    }

//    //4.spring定时任务线程池
//    @Test
//    public void testTaskScheduler(){
//        Runnable task = new Runnable() {
//            @Override
//            public void run() {
//                logger.debug("Hello ScheduledExecutorService");
//            }
//        };
//        Date startTime = new Date(System.currentTimeMillis() +10000);
//        taskScheduler.scheduleAtFixedRate(task,startTime,100);
//
//        sleep(30000);
//    }

}
