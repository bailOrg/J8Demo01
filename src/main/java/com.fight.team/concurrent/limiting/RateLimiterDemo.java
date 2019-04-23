package com.fight.team.concurrent.limiting;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 限流demo
 *
 * @author bail
 * @date 2019/4/23
 */
public class RateLimiterDemo {

    public static void main(String[] args) throws Exception {
        testAcquire();
    }

    /**
     * 使用sleep 阻塞的形式限流
     */
    static void testAcquire() {
        RateLimiter limiter = RateLimiter.create(1);
        long start = System.currentTimeMillis();
        ThreadPoolExecutor pool = new ThreadPoolExecutor(10,20,0, TimeUnit.SECONDS, new LinkedBlockingDeque<>(10));
        for (int j=0; j<10; j++)
            pool.execute(() -> {
                for (int i=0; i<3; i++) {
                    double waitTime = limiter.acquire(1);
                    System.out.println(Thread.currentThread().getName() + " waitTime:" + Math.round(waitTime) + "s 执行时间:" + (System.currentTimeMillis()-start)/1000 + "s");
                }
            });
    }
}
