package com.coding.collunsafe;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

// 任何想修改JDK源码都是不可取的

// ConcurrentModificationException
// 并发下 HashMap 不安全
// 解决方案：Map<String,String> map = new ConcurrentHashMap<>();
public class UnSafeMap {
    public static void main(String[] args) {
        // 在开发中会这样使用HashMap吗？ 不会  一开始就知道 100大小的容量
        // 根据实际的业务谁初始值

        // 人生如程序，不是选择，就是循环，学习和总结十分重要！
        // Map<String,String> map = new HashMap<>();
        Map<String,String> map = new ConcurrentHashMap<>();

        // 加载因子，初试值
        // Map<String,String> map = new HashMap<>(100,0.75);
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,5));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
}
