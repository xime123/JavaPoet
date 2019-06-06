package com.arouter.javapoet.queue.delayqueue;

import java.util.concurrent.DelayQueue;

public class Wangba implements Runnable{
    private DelayQueue<Wangmin> delayQueue=new DelayQueue<>();
    private boolean yingye =true;
    public void shangji(Wangmin wm){
        System.out.println(wm.getName()+"::"+wm.getId()+"上机了 " );
        delayQueue.add(wm);
    }

    @Override
    public void run() {
        while (yingye){
            try {
                Wangmin wm=delayQueue.take();
                System.out.println(wm.getName()+"::"+wm.getId()+"下机机了 " );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
