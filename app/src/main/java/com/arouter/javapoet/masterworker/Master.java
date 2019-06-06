package com.arouter.javapoet.masterworker;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Master {
    private ConcurrentLinkedQueue<Task> taskQueue = new ConcurrentLinkedQueue<>();
    private Map<String, Thread> wokers = new HashMap<>();
    private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<>();

    public Master(Woker woker, int wokerCount) {
        woker.setResultMap(resultMap);
        woker.setTaskQueue(taskQueue);
        for (int i = 0; i < wokerCount; i++) {
            wokers.put("子节点" + i, new Thread(woker));
        }
    }

    public void submit(Task task) {
        taskQueue.add(task);
    }

    public void excute() {
        for (Map.Entry<String, Thread> me : wokers.entrySet()) {
            me.getValue().start();
        }
    }

    public boolean isCompelte() {
        for (Map.Entry<String, Thread> me : wokers.entrySet()) {
            if (me.getValue().getState() != Thread.State.TERMINATED) {
                return false;
            }
        }
        return true;
    }

    public ConcurrentHashMap<String, Object> getResultMap() {
        return resultMap;
    }
}
