package com.coding.demo02;

/**
 * 题目：现在有两个线程，操作一个初始值诶0的变量
 *      一个线程+1,一个线程 -1. 判断什么时候 +1 ，什么时候 -1
 *      交替10次
 *
 * 方法论：
 *
 * 生产者消费者模型：判断，干活，通知
 */
public class A {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    data.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                try {
                    data.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    data.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                try {
                    data.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}

class Data{
    private int num = 0;

    // +1
    public synchronized void increment() throws InterruptedException {
        // 判断 // if 会导致虚假唤醒
        while (num != 0){
            this.wait();
        }
        // 干活
        num++;
        System.out.println(Thread.currentThread().getName()+"\t"+num);
        // 通知
        this.notifyAll();
    }

    // -1
    public synchronized void decrement() throws InterruptedException {
        // 判断
        while (num == 0){
            this.wait();
        }
        // 干活
        --num;
        System.out.println(Thread.currentThread().getName()+"\t"+num);
        // 通知
        this.notifyAll();
    }
}
