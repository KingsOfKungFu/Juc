package com.coding.lock8;

import java.util.concurrent.TimeUnit;

/**
 *  4，两部手机，请问先打电话还是短信？
 */
public class Test4 {
    public static void main(String[] args) {
        // 两个对象，互不干预
        Phone4 phone1 = new Phone4();
        Phone4 phone2 = new Phone4();
        new Thread(()->{
            phone1.sendEmail();
        },"A").start();

        // 干扰
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            phone2.sendMS();
        }).start();
    }
}

class Phone4 {
    // 被 synchronized 修饰的方法、锁的对象是方法的调用者、调用者不同，没有关系，量个方法用得不是同一个锁！
    public synchronized void sendEmail() {
        // 善意的延迟
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sendEmail");
    }

    public synchronized void sendMS() {
        System.out.println("sendMS");
    }
}
