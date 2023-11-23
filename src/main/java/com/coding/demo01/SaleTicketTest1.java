package com.coding.demo01;

/**
 * 卖票 自己会写 3个售票员卖出30张票
 * 企业中禁止这样写，coding：企业级开发！
 *
 * 多线程编程的固定套路：
 *      1，高内聚，低耦合
 *      2，线程    操作(调用对外暴露的方法)   资源类(要点)
 */
public class SaleTicketTest1 {
    public static void main(String[] args) {
        // 资源类
        SaleTicket saleTicket = new SaleTicket();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    saleTicket.saleTicket();
                }
            }
        },"A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    saleTicket.saleTicket();
                }
            }
        },"B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    saleTicket.saleTicket();
                }
            }
        },"C").start();
    }

}

// 属性和方法    高内聚
// 资源类
class SaleTicket{
    private int number = 30;

    // sychronized 本质排队
    public synchronized void saleTicket(){
        if(number > 0){
            System.out.println(Thread.currentThread().getName() + "卖出第" + (number--) + "还剩下：" + number + "张票");
        }
    }

}