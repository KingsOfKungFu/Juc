package com.coding.rwdemo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteDemo {
    public static void main(String[] args) {
        MyCache2 myCache = new MyCache2();

        // 多个线程同时进行读写
        // 五个线程在写  线程是CPU调度的
        for (int i = 0; i <= 5; i++) {
            final int temp = i;
            new Thread(()->{
                myCache.put(temp+"", temp+"");
            },String.valueOf(i)).start();
        }

        // 五个线程在读
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(()->{
                myCache.get(temp+"");
            },String.valueOf(i)).start();
        }
    }
}

class MyCache {
    private volatile Map<String,Object> map = new HashMap<>();
    // 没有加读写锁的时候，第一个线程还没写入完成，可能会存在其它写入

    // 写。独占
    public void put(String key, String value){
        System.out.println(Thread.currentThread().getName()+"写入"+key);
        map.put(key,value);
        // 存在别的线程插队
        System.out.println(Thread.currentThread().getName()+"写入完成");
    }

    // 读
    public void get(String key){
        System.out.println(Thread.currentThread().getName()+"读取"+key);
        Object result = map.get(key);
        System.out.println(Thread.currentThread().getName()+"读取结果"+result);
    }
}

class MyCache2 {
    private volatile Map<String,Object> map = new HashMap<>();

    // 读写锁
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    // 写。独占
    public void put(String key, String value){
        // 共享锁和独占锁原理是啥？
        readWriteLock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"写入"+key);
            map.put(key,value);
            // 存在别的线程插队
            System.out.println(Thread.currentThread().getName()+"写入完成");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }

    // 读
    public void get(String key){
        // 共享锁和独占锁原理是啥？
        readWriteLock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"读取"+key);
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"读取结果："+result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }
    }
}