package com.coding.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 5，两个静态同步方法，同一部手机，请问先打印邮件还是短信？
 */
public class Test5 {
    public static void main(String[] args) {
        Phone5 phone = new Phone5();

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
            phone.sendMS();
        }, "B").start();
    }
}

class Phone5 {
    // 被shnchronized 和 static 修饰的方法，锁的对象是类的 Class 对象！唯一的
    public static synchronized void sendEmail() {
        // 善意的延迟
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sendEmail");
    }

    public static synchronized void sendMS() {
        System.out.println("sendMS");
    }
}
