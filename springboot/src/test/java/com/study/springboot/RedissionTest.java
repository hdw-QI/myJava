package com.study.springboot;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RLock;
import org.redisson.api.RQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 胡代伟
 * @description Redission的使用
 * @date 2024-01-25 16:56
 */
@SpringBootTest
@Slf4j
public class RedissionTest {

    //1、Redisson分布式锁实例
    //分布式锁：实现分布式环境下的互斥访问，如缓存更新、资源分配等。
    // 注入 RedissonClient
    @Autowired
    RedissonClient redissonClient;
    @Test
    void testLock() {
        // 获取分布式锁
        RLock lock = redissonClient.getLock("myLock");
        try {
            // 尝试获取锁
            boolean locked = lock.tryLock();
            if (locked) {
                // 执行临界区操作
                log.info("获取锁成功，执行临界区操作");

                // 锁定时间为2秒
                Thread.sleep(2000);

                // 释放锁
                lock.unlock();
            } else {
                log.info("获取锁失败，等待重试");
            }
        } catch (InterruptedException  e) {
            e.printStackTrace();
        }
    }
    //用多个线程测试分布式锁
    @Test
    void testLockManyThread() {
        for (int i = 0; i < 64; i++) {
//            new Thread(() -> {
//                testLock();
//            }).start();
            new Thread(this::testLock).start();
        }
    }


    //2、 Redisson分布式队列实例
    //分布式队列：实现分布式环境下的异步处理，如任务调度、消息推送等。
    @Test
    void testQueue() {
        // 创建分布式队列
        RQueue<String> queue = redissonClient.getQueue("myQueue");

        // 向队列中添加元素
        for (int i = 1; i <= 10; i++) {
            queue.offer(String.valueOf(i));
        }

        // 从队列中弹出元素
        for (int i = 1; i <= 10; i++) {
            log.info("弹出元素：{}" , queue.poll());
        }
    }

    //3、Redisson分布式计数器实例
    //分布式计数器：实现分布式环境下的统计计数，如访问量统计、事件计数等。
    @Test
    void testCounter() {
        // 创建分布式计数器
        RAtomicLong counter = redissonClient.getAtomicLong("myCounter");

        // 递增计数器
        long value = counter.incrementAndGet();
        log.info("递增计数器值：" + value);

        // 获取计数器值
        value = counter.get();
        log.info("获取计数器值：" + value);

        // 递减计数器
        value = counter.decrementAndGet();
        log.info("递减计数器值：" + value);
    }
}
