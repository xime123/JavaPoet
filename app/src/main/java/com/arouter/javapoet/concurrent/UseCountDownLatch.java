package com.arouter.javapoet.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * 正如每个Java文档所描述的那样，CountDownLatch是一个同步工具类，它允许一个或多个线程一直等待，
 * 直到其他线程的操作执行完后再执行。在Java并发中，countdownlatch的概念是一个常见的面试题,
 * 所以一定要确保你很好的理解了它。
 */
public class UseCountDownLatch {
    public static void main(String args[]) {
        int waitThreadCount = 2;//需要等待的线程个数
        final CountDownLatch countDownLatch = new CountDownLatch(waitThreadCount);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("t1线程开始初始化执行");
                    Thread.sleep(3003);
                    countDownLatch.await();
                    System.out.println("t1线程继续执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("t2线程开始初始化执行");
                    Thread.sleep(3003);
                    countDownLatch.countDown();
                    System.out.println("t2线程继续执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("t3线程开始初始化执行");
                    Thread.sleep(16003);
                    countDownLatch.countDown();
                    System.out.println("t3线程继续执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}
