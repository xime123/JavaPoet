package com.arouter.javapoet.masterworker;

public class IdWoker extends Woker {
    @Override
    public Object handleTask(Task task) {
        Object o = null;
        try {
            Thread.sleep(500);
            o = task.getId();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return o;
    }
}
