package com.coding.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8，一个普通同步方法，一个静态同步方法，2部手机，请问先打印邮件还是短信？
 */
public class Test8 {
    public static void main(String[] args) {
        // 两个对象，互不干预
        Phone8 phone = new Phone8();
        Phone8 phone2 = new Phone8();

        // 我们这里两个线程使用的是同一个对象。两个线程是一把锁！先调用的先执行！
        new Thread(() -> { // 一开始就执行了
            phone.sendEmail();
        }, "A").start();

        // 干扰
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> { // 一秒后执行
            phone2.sendMS();
        }, "B").start();

    }
}

class Phone8{

    // CLASS
    public static synchronized void sendEmail() {
        // 善意的延迟
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sendEmail");
    }

    // 对象
    // 普通同步方法
    public synchronized void sendMS() {
        System.out.println("sendMS");
    }

}