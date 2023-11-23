package com.coding.demo01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTicketTest2 {

    public static void main(String[] args) {
        // 并发： 多线程操作同一个资源类
        SaleTicket2 saleTicket = new SaleTicket2();

        // lambda表达式，链式编程，流式计算
        // lambda表达式, ()->{} 自动推断类型

        // IDEA 一定要设置 JDK 版本为1.8
        new Thread(() -> {
            for (int i = 1; i < 40; i++) saleTicket.saleTicket();
        },"A").start();

        new Thread(() -> {
            for (int i = 1; i < 40; i++) saleTicket.saleTicket();
        },"B").start();

        new Thread(() -> {
            for (int i = 1; i < 40; i++) saleTicket.saleTicket();
        },"C").start();
    }

}

class SaleTicket2{
    private int number = 30;

    private Lock lock = new ReentrantLock();

    public void saleTicket(){
        // 锁要配对
        lock.lock();
        try{
            // 业务代码 写这里
            if(number > 0){
                System.out.println(Thread.currentThread().getName() + "卖出第" + (number--) + "还剩下：" + number + "张票");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();; // 解锁
        }
    }
}