/**
 * @(#)AtomicTest.java, 2018-05-23.
 * <p>
 * Copyright 2018 Youdao, Inc. All rights reserved.
 * YOUDAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.company.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * AtomicTest
 *
 * @author Mao Wenbei
 */
public class AtomicTest {
    public static void main(String[] args) {

        final int loopcount = 10000;
        int threadcount = 10;

        final NonSafeSeq seql = new NonSafeSeq();
        final SafeSeq safeSeq = new SafeSeq();

        final CountDownLatch l = new CountDownLatch(threadcount);
        for (int i = 0; i < threadcount; i++) {
            final int index = i;
            new Thread(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    for (int j = 0; j < loopcount; j++) {
                        seql.inc();
                        safeSeq.inc();
                    }
                    System.out.println("Finished: " + index);
                    l.countDown();
                }
            }).start();
        }

        try {
            l.await();
        } catch (InterruptedException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        System.out.println("Both have finished...");
        System.out.println("NonSafeSeq:" + seql.get());
        System.out.println("SageSeq:" + safeSeq.get());
    }

}

class NonSafeSeq {
    private long count = 0;

    public void inc() {
        count++;
    }

    public long get() {
        return count;
    }
}

class SafeSeq {
    private AtomicLong count = new AtomicLong(0);

    public void inc() {
        count.incrementAndGet();
    }

    public Long get() {
        return count.longValue();
    }
}
