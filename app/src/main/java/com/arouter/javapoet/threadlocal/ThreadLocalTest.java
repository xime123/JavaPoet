package com.arouter.javapoet.threadlocal;

public class ThreadLocalTest {
    public static void main(String args[]) {
        final ThreadLocal<String> threadLocal=new ThreadLocal<>();
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    threadLocal.set("zhansan");
                    System.out.println("t1 threadlocal get :"+threadLocal.get());
                }
            }
        },"t1");
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("t2 threadlocal get :"+threadLocal.get());
                }
            }
        },"t2");
        t1.start();
        t2.start();
    }
}
