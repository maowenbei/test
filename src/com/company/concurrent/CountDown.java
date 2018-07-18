/**
 * @(#)CountDown.java, 2018-05-23.
 * <p>
 * Copyright 2018 Youdao, Inc. All rights reserved.
 * YOUDAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.company.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * CountDown
 *
 * @author Mao Wenbei
 */
public class CountDown {

    public static void main(String[] args) {
        int count = 10;
        final CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            final int index = i;
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        Thread.currentThread().sleep(20 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread " + index + " has finished...");
                    countDownLatch.countDown();
                }
            }).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("all threads have finished.");
    }
}

