package com.coding.demo02;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多个线程启动 A-- B -- C
 * 三个线程依次打印
 * A 5次
 * B 10次
 * C 15次
 * 依次循环
 * 精准通知线程消费
 */
public class C {
    public static void main(String[] args) {
        Data3 data = new Data3();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.print5();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.print10();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.print15();
            }
        },"C").start();
    }
}

class Data3{
    private int num = 1;
    // 定义锁
    Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print5(){
        lock.lock();
        try{
            // 判断
            while (num != 1){
                condition1.await();
            }
            // 干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            // 第一个线程通知第二个线程，第二个线程通知第三个。。。 计数器
            num = 2;
            // 通知第二个线程干活，指定谁干活
            condition2.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }
    }

    public void print10(){
        lock.lock();
        try{
            // 判断
            while (num != 2){
                condition2.await();
            }
            // 干活
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            // 第一个线程通知第二个线程，第二个线程通知第三个。。。 计数器
            num = 3;
            // 通知第三个线程干活，指定谁干活
            condition3.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try{
            // 判断
            while (num != 3){
                condition3.await();
            }
            // 干活
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            // 第一个线程通知第二个线程，第二个线程通知第三个。。。 计数器
            num = 1;
            // 通知第一个线程干活，指定谁干活
            condition1.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }
    }
}