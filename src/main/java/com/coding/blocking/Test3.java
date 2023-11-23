package com.coding.blocking;

import java.util.concurrent.ArrayBlockingQueue;

public class Test3 {
    public static void main(String[] args) throws InterruptedException {

        // 队列的大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3); // 阻塞队列

        // 一直阻塞。超过3秒我就不等了, 业务必须要做！
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        // blockingQueue.put("d");

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take()); // 阻塞等待拿出元素
    }
}
