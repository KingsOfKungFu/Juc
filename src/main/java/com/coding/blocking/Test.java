package com.coding.blocking;

import java.util.concurrent.ArrayBlockingQueue;

public class Test {
    public static void main(String[] args) {
        // 队列的大小
        // 阻塞队列
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        // add返回布尔值
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

        System.out.println(blockingQueue.element());

        System.out.println(blockingQueue.remove());

        System.out.println(blockingQueue.element());

        blockingQueue.remove();

        System.out.println(blockingQueue.element());

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
    }
}
