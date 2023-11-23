package com.coding.blocking;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Test4 {
    public static void main(String[] args) throws InterruptedException {

        // 队列的大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3); // 阻塞队列

        // 设置超时的时间
        blockingQueue.offer("a");
        blockingQueue.offer("b");
        blockingQueue.offer("c");
        // 超过3秒就不等待了
        blockingQueue.offer("d",3L, TimeUnit.SECONDS); // 不让他一直等待，然后也不想返回false，设置等待时间

        System.out.println(blockingQueue.poll()); // a
        System.out.println(blockingQueue.poll()); // b
        System.out.println(blockingQueue.poll()); // c
        System.out.println(blockingQueue.poll(3L,TimeUnit.SECONDS)); // 阻塞
    }
}
