/**
 * @(#)ReentrantReadWriteLockTest.java, 2018-05-23.
 * <p>
 * Copyright 2018 Youdao, Inc. All rights reserved.
 * YOUDAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.company.concurrent;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁ReadWriteLock
 * <p>
 * 读锁可以有很多个锁同时上锁，只要当前没有写锁；
 * 写锁是排他的，上了写锁，其他线程既不能上读锁，也不能上写锁；同样，需要上写锁的前提是既没有读锁，也没有写锁；
 * 两个写锁不能同时获得无需说明，下面一段程序说明下上了读锁以后，其他线程需要上写锁也无法获得
 */
public class ReentrantReadWriteLockTest {

    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        final Lock rlock = lock.readLock();
        final Lock wlock = lock.writeLock();

        final CountDownLatch l = new CountDownLatch(2);

        // start r thread
        new Thread(new Runnable() {

            @Override
            public void run() {

                System.out.println(new Date() + "now to get rlock");
                rlock.lock();

                try {
                    Thread.currentThread().sleep(20 * 1000);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }

                System.out.println(new Date() + "now to unlock rlock");
                rlock.unlock();

                l.countDown();
            }
        }).start();

        // start w thread
        new Thread(new Runnable() {

            @Override
            public void run() {

                System.out.println(new Date() + "now to get wlock");
                wlock.lock();

                System.out.println(new Date() + "now to unlock wlock");
                wlock.unlock();

                l.countDown();
            }
        }).start();

        try {
            l.await();
        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        System.out.println(new Date() + "finished");
    }

}
