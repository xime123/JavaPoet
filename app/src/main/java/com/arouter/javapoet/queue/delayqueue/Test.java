package com.arouter.javapoet.queue.delayqueue;

public class Test {
    public static void main(String args[]) {
        Wangba wb=new Wangba();
        wb.shangji(new Wangmin("张三",1234,3*1000+System.currentTimeMillis()));
        wb.shangji(new Wangmin("里斯",4634,6*1000+System.currentTimeMillis()));
        wb.shangji(new Wangmin("貂蝉",8934,4*1000+System.currentTimeMillis()));
        new Thread(wb).start();
    }
}
