package com.coding.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 3,新增一个普通hell方法没有同步，请问先打印邮件还是hello
 */
public class Test3 {
    public static void main(String[] args) {
        Phone3 phone = new Phone3();
        //
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
            phone.hello();
        },"B").start();
    }
}

class Phone3{
    // 被 synchronized 修饰的方法、锁的对象是方法的调用者、
    public synchronized void sendEmail() {
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
    // 新增的方法没有被 synchronized 修饰，不是同步方法，所以不需要等待，其他线程用了一个把锁
    public void hello() {
        System.out.println("hello");
    }
}