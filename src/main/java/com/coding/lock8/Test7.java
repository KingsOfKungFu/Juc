package com.coding.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 7，一个普通同步方法，一个静态同步方法，同一部手机，请问先打印邮件还是短信？
 */
public class Test7 {
    public static void main(String[] args) {
        Phone7 phone = new Phone7();
        new Thread(()->{
            phone.sendEmail();
        },"A").start();
        // 干扰
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            phone.sendMS();
        },"B").start();
    }
}


// 解析：连个方法的锁不同，所以不阻塞
class Phone7{

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