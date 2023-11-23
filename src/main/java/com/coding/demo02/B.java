package com.coding.demo02;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// lock版生产者消费者
public class B {
    public static void main(String[] args) {
        // 新版
        Data2 data = new Data2();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                data.increment();
            }
        },"A").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                data.decerment();
            }
        },"B").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                data.increment();
            }
        },"C").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                data.decerment();
            }
        },"D").start();
    }
}

class Data2{
    private int num = 0;
    Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    // +1
    public void increment(){
        lock.lock();

        try{
            // 判断
            while (num != 0){
                condition.await(); // 等待
            }
            // 干活
            num++;
            System.out.println(Thread.currentThread().getName()+"\t"+num);
            // 通知
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock(); // 解锁
        }
    }

    // -1
    public void decerment(){
        // 加锁
        lock.lock();
        try{
            // 判断
            while (num == 0){
                condition.await(); // 等待
            }
            // 干活
            num--;
            System.out.println(Thread.currentThread().getName()+"\t"+num);
            // 通知
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}