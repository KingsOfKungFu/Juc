package com.coding.lock8;

import java.util.concurrent.TimeUnit;

/**
 *  1，标准访问，请问先打印邮件1还是短信2？
 */
public class Test2 {
    public static void main(String[] args) {
        Phone2 phone = new Phone2();
        // 我们这里两个线程使用的是同一个对象。两个线程是一把锁！先调用的先执行
        new Thread(()->{
            phone.sendEmail();
        },"A").start();

        // 干扰
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(()->{
            phone.sendMS();
        },"B").start();
    }

}

class Phone2{
    // 被 syncronized 修饰的方法，锁的对象是方法的调用者
    public synchronized void sendEmail(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("sendEmail");
    }

    public synchronized void sendMS(){
        System.out.println("sendMS");
    }
}