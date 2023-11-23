package com.coding.demo03;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        // 计数器
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+" Start");
                countDownLatch.countDown(); // 计数器 -1
            },String.valueOf(i)).start();
        }
        // 阻塞等待计数器归零
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+ " End");
    }

    public void test1(){
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"Start");
            },String.valueOf(i)).start();
        }
        System.out.println(Thread.currentThread().getName()+ " End");
    }
}
