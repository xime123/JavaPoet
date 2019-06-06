package com.arouter.javapoet.masterworker;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class Test {
    public static void main(String args[]){
        Woker woker=new IdWoker();
        Master master=new Master(woker,10);
        Random random=new Random();
        for(int i=0;i<100;i++){
            Task task=new Task();
            task.setId(i);
            task.setName("任务"+i);
            task.setPrice(random.nextInt(1000));
            master.submit(task);
        }
        master.excute();
        while (true){
            if(master.isCompelte()){
                System.out.println("100个任务执行完成");
                ConcurrentHashMap<String ,Object>result=master.getResultMap();
                System.out.println("result size="+result.size());
                for(Map.Entry<String,Object> me:result.entrySet()){
                    System.out.println("**************************");
                    System.out.println("已完成的任务名称： ="+me.getKey());
                    System.out.println("已完成的任务结果： ="+me.getValue());
                    System.out.println("**************************");
                }

                break;
            }
        }
    }
}
