package com.arouter.javapoet.concurrent;


import java.util.concurrent.CyclicBarrier;

/**
 * 所有线程都调用了awit 大家才会执行各自awit后面的代码
 */
public class UseCyclicBarrier {
    public static void main(String args[]){
        final CyclicBarrier cyclicBarrier=new CyclicBarrier(3);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3003);
                    System.out.println("t1准备ok");
                    cyclicBarrier.await();
                    System.out.println("t1 go...");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(6003);

                    System.out.println("t2准备ok");
                    cyclicBarrier.await();
                    System.out.println("t2go...");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10003);

                    System.out.println("t3准备ok");
                    cyclicBarrier.await();
                    System.out.println("t3go.。。。。");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        t1.start();
        t2.start();
        t3.start();
    }
}
