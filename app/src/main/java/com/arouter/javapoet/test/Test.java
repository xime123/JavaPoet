package com.arouter.javapoet.test;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Iterator;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Test {
    public static final String TAG_TEST = "tag_test";

    public void test() {
        Log.e("TEST", "测试一下");
    }

    public static void main(String args[]) {
        DelayQueue<DelayBean> delayQueue = new DelayQueue<DelayBean>();
        DelayBean delayBean = new DelayBean(2000);
        delayBean.setFlag(1);
        DelayBean delayBean1 = new DelayBean(4000);
        delayBean1.setFlag(2);
        DelayBean delayBean2 = new DelayBean(6000);
        delayBean2.setFlag(3);
        delayQueue.add(delayBean);

        delayQueue.add(delayBean1);

        delayQueue.add(delayBean2);
        Iterator<DelayBean> iterator = delayQueue.iterator();
//        while (iterator.hasNext()) {
//            System.out.println("开始遍历. size=" + delayQueue.size());
//            DelayBean item = iterator.next();
//            if (item.getFlag() == 3) {
//                delayQueue.remove(item);
//                System.out.println("移除了被标记的item");
//                System.out.println("移除后. size=" + delayQueue.size());
//                break;
//            }
//        }
//        System.out.println("遍历结束. size=" + delayQueue.size());size
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        DelayBean bean = delayQueue.take();
                        System.out.println("flag=" + bean.getFlag());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }

    public static class DelayBean implements Delayed {
        private long insertTime;
        private long delayTime;
        private int flag;

        public DelayBean(long delayTime) {
            this.insertTime = System.currentTimeMillis();
            this.delayTime = delayTime;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public int getFlag() {
            return flag;
        }

        @Override
        public long getDelay(@NonNull TimeUnit unit) {
            long r = unit.convert(this.insertTime + delayTime - System.currentTimeMillis(), TimeUnit.NANOSECONDS);
            return r;
        }

        @Override
        public int compareTo(@NonNull Delayed o) {
            //比较 1是放入队尾  -1是放入队头
            DelayBean that = (DelayBean) o;
            if (this.insertTime + this.delayTime > that.insertTime + that.delayTime) {
                return 1;
            } else if (this.insertTime + this.delayTime == that.insertTime + that.delayTime) {
                return 0;
            } else {
                return -1;
            }
        }
    }
}
