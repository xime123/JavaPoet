package com.arouter.javapoet.masterworker;

public class PriceWoker extends Woker {

    @Override
    public Object handleTask(Task task) {
        Object o = null;
        try {
            Thread.sleep(500);
            o = task.getPrice();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return o;
    }
}
