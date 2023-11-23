package com.coding.callabledemo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class Test1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Threaa(Runnable)
        // Threaa(RunnableFuture)
        // Threaa(FutureTask)
        MyThread myThread = new MyThread();
        FutureTask task = new FutureTask(myThread); // 适配类

        // 会打印几次 end
        new Thread(task,"A").start();
        new Thread(task,"B").start(); // 执行线程。细节1：结果缓存！效率提高N倍

        System.out.println(task.get()); // 获得返回值，get()

        // 细节2：tast.get() 获取的方法一般放到最后，保证程序平稳运行的效率，因为他会阻塞
        // 线程是一个耗时的线程，不重要！
    }
}

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("end");
        TimeUnit.SECONDS.sleep(3);

        return 1024;
    }
}