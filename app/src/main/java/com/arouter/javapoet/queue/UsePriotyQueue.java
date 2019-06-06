package com.arouter.javapoet.queue;

import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.PriorityBlockingQueue;

public class UsePriotyQueue {
    public static void main(String args[]) throws Exception {
        PriorityBlockingQueue<Task> taskQueue = new PriorityBlockingQueue<>(5, new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                if (o1.getId() > o2.getId()) {
                    return 1;
                } else if (o1.getId() == o2.getId()) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        Task t1 = new Task(3, "t1");
        Task t2 = new Task(1, "t2");
        Task t3 = new Task(4, "t3");
        Task t4 = new Task(4, "t4");
        taskQueue.add(t1);
        taskQueue.add(t2);
        taskQueue.add(t3);
        taskQueue.add(t4);
        Iterator<Task> iterator = taskQueue.iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            System.out.println(task.getName());
        }
//        while (true){
//            Thread.sleep(2000);
//            Task task= taskQueue.take();
//            System.out.println(task.getName());
//        }
    }
}
