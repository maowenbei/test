/**
 * @(#)TestWatiNotify.java, 2018-05-23.
 * <p>
 * Copyright 2018 Youdao, Inc. All rights reserved.
 * YOUDAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.company.concurrent;

/**
 * TestWatiNotify
 *
 * @author Mao Wenbei
 *
 */
public class TestWatiNotify {

    public static Object object = new Object();
    public static void main(String[] args) {
        ThreadA thread1 = new ThreadA();
        ThreadB thread2 = new ThreadB();

        thread1.start();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread2.start();
    }

    static class ThreadA extends Thread{
        @Override
        public void run() {
            // Thread1 先运行，拿到锁
            synchronized (object) {
                try {
                    System.out.println("ThreadA-"+Thread.currentThread().getName()+"获取到了锁");
                    System.out.println("ThreadA-"+Thread.currentThread().getName()+"调用object.wait()释放锁");
                    // Thread释放锁
                    object.wait();
                    System.out.println("ThreadA-"+Thread.currentThread().getName()+"再次获取到了锁");
                } catch (InterruptedException e) {
                }
            }
            System.out.println("ThreadA-"+Thread.currentThread().getName()+"结束");
        }
    }

    static class ThreadB extends Thread{
        @Override
        public void run() {
            synchronized (object) {
                try {
                    System.out.println("ThreadB-"+Thread.currentThread().getName()+"获取到了锁");
                    object.notify();
                    System.out.println("ThreadB-"+Thread.currentThread().getName()+"调用了object.notify()");
                    System.out.println("ThreadB-"+Thread.currentThread().getName()+"sleep");
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println("ThreadB-"+Thread.currentThread().getName()+"释放了锁");
        }
    }
}