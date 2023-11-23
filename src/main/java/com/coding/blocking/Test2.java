package com.coding.blocking;

import java.util.concurrent.ArrayBlockingQueue;

public class Test2 {
    public static void main(String[] args) {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        // 等待(一直等待，超时就不等待)
        System.out.println(blockingQueue.offer("d"));

        // 查看队首
        System.out.println(blockingQueue.peek());

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll()); // null

        System.out.println(blockingQueue.peek()); // 查看队首 null
    }
}
