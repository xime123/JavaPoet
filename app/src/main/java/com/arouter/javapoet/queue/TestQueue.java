package com.arouter.javapoet.queue;

public class TestQueue {
    public static void main(String args[]){
        final MyQueue queue=new MyQueue(5);
        queue.putElement("a");
        queue.putElement("b");
        queue.putElement("c");
        queue.putElement("d");
        queue.putElement("e");
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    queue.putElement("f");
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
                    queue.removeElement();
                }
            }
        },"t2");
        t1.start();
        t2.start();
    }
}
