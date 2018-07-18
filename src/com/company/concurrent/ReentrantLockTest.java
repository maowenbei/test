/**
 * @(#)ReentrantLockDemo.java, 2018-05-23.
 * <p>
 * Copyright 2018 Youdao, Inc. All rights reserved.
 * YOUDAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.company.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 通过lock.lock和lock.unlock来获取锁和释放锁
 * <p>
 * 注意必须将unlock放在finally块里面
 * <p>
 * ReentrantLock的好处（相对synchronized）：
 * <p>
 * 1. 是更好的性能
 * 2. 提供同一个lock对象上不同condition的信号通知
 * 3. 还提供lockInterruptibly这样支持响应中断的加锁过程，意思是说你试图去加锁，但是当前锁被其他线程hold住，然后你这个线程可以被中断；
 */
public class ReentrantLockTest {

    public static void main(String[] args) {

        final int loopcount = 10000;
        int threadcount = 10;

        final SafeSeqWithLock seq = new SafeSeqWithLock();

        final CountDownLatch l = new CountDownLatch(threadcount);

        for (int i = 0; i < threadcount; ++i) {
            final int index = i;
            new Thread(new Runnable() {

                @Override
                public void run() {
                    for (int j = 0; j < loopcount; ++j) {
                        seq.inc();
                    }
                    System.out.println("finished : " + index);
                    l.countDown();

                }
            }).start();
        }

        try {
            l.await();
        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        System.out.println("both have finished....");

        System.out.println("SafeSeqWithLock:" + seq.get());

    }
}

class SafeSeqWithLock {
    private long count = 0;

    private ReentrantLock lock = new ReentrantLock();

    public void inc() {
        lock.lock();

        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public long get() {
        return count;
    }
}
