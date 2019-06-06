package com.arouter.javapoet.queue;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class MyQueue {
    private LinkedList<String> linkedList = new LinkedList<>();
    private AtomicInteger count = new AtomicInteger(0);
    private ReentrantLock lock = new ReentrantLock();
    private int minSize = 0;
    private int maxSize;

    public MyQueue(int size) {
        this.maxSize = size;
    }

    public void putElement(String element) {
        synchronized (lock) {
            while (count.get() == this.maxSize) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.linkedList.add(element);
            count.incrementAndGet();
            System.out.println("添加了一个元素:" + element);
            lock.notify();
        }
    }

    public String removeElement() {
        synchronized (lock) {
            while (count.get() == minSize) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String element = linkedList.removeFirst();
            count.incrementAndGet();
            System.out.println("移除了一个元素:" + element);
            lock.notify();
            return element;
        }
    }
}
