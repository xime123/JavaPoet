package com.arouter.javapoet.queue.delayqueue;

import android.support.annotation.NonNull;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Wangmin implements Delayed {
    private String name;
    private int id;
    private long endTime;

    public Wangmin(String name, int id, long endTime) {
        this.name = name;
        this.id = id;
        this.endTime = endTime;
    }

    @Override
    public long getDelay(@NonNull TimeUnit unit) {
        return endTime-System.currentTimeMillis();
    }

    @Override
    public int compareTo(@NonNull Delayed o) {
        Wangmin wm=(Wangmin)o;
        return this.endTime-wm.getEndTime()>0?1:0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
