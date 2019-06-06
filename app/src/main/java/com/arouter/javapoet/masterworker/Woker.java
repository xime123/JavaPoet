package com.arouter.javapoet.masterworker;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class Woker implements Runnable {
    private String name;
    private ConcurrentHashMap<String, Object> resultMap;
    private ConcurrentLinkedQueue<Task> taskQueue;

    public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    public void setTaskQueue(ConcurrentLinkedQueue<Task> taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        while (true) {
            Task task = taskQueue.poll();
            if (task == null) break;
            Object result = handleTask(task);
            resultMap.put(task.getName(), result);
        }

    }



    public abstract Object handleTask(Task task);
}
