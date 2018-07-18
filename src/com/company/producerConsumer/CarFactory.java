/**
 * @(#)CarFactory.java, 2018-04-12.
 * <p>
 * Copyright 2018 Youdao, Inc. All rights reserved.
 * YOUDAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.company.producerConsumer;

import java.util.ArrayList;
import java.util.List;

/**
 * CarFactory
 * 完成以下汽车工厂的代码,通过main方法构建工厂并输出.
 * 一个工厂由生产车轮的工人,生产车身的工人以及装配工人装配工人构成.
 * 生产车轮的工人每秒钟生产一个车轮(WHEEL)然后随机交给一个装配工人
 * 生产车身的工人每秒钟生产一辆车身(BODY)然后随机交给一个装配工人,
 * 每个装配工人每次集齐了一个车身和四个车轮后装配一辆车并输出"装配工人[ID]装配完成了一辆车.
 *
 * @author Mao Wenbei
 */
public class CarFactory {

    private List<AssembleWorker> assembleWorkerList = new ArrayList<>();
    private List<BodyWorkerRunnable> bodyWorkerRunnableList = new ArrayList<>();
    private List<WheelWorkerRunnable> wheelWorkerRunnableArrayList = new ArrayList<>();

    public CarFactory(int bodyWorkerNum, int wheelWorkerNum, int assembleWorkerNum) {
        //TODO+初始化一个汽车工厂
        for (int i = 0; i < assembleWorkerNum; i++) {
            assembleWorkerList.add(new AssembleWorker(i));
        }
        for (int i = 0; i < bodyWorkerNum; i++) {
            bodyWorkerRunnableList.add(new BodyWorkerRunnable(i, assembleWorkerList));
        }
        for (int i = 0; i < wheelWorkerNum; i++) {
            wheelWorkerRunnableArrayList.add(new WheelWorkerRunnable(i, assembleWorkerList));
        }
    }

    public void start() {
        //TODO+运转并输出
        for (BodyWorkerRunnable bodyWorkerRunnable : bodyWorkerRunnableList) {
            new Thread((bodyWorkerRunnable)).start();
        }
        for (WheelWorkerRunnable wheelWorkerRunnable : wheelWorkerRunnableArrayList) {
            new Thread((wheelWorkerRunnable)).start();
        }
    }

    public static void main(String[] args) {
        //构建一个汽车工厂并输出
        CarFactory factory = new CarFactory(2, 8, 20);
        factory.start();
    }
}

class AssembleWorker {
    private int threadId;
    private int bodyCount = 0;
    private int wheelCount = 0;

    public AssembleWorker(int threadId) {
        this.threadId = threadId;
    }

    public void addWheel() {
        synchronized (this) {
            wheelCount++;
            produce();
        }
    }

    public void addBody() {
        synchronized (this) {
            bodyCount++;
            produce();
        }
    }

    public void produce() {
        if (bodyCount >= 1 && wheelCount >= 4) {
            bodyCount -= 1;
            wheelCount -= 4;
            System.out.println("装配工人[" + threadId + "]装配完成了一辆车.");
        }
    }
}

class BodyWorkerRunnable implements Runnable {
    private int threadId;

    private int assembleWorkerNum;

    private List<AssembleWorker> assembleWorkerList;

    public BodyWorkerRunnable(int threadId, List<AssembleWorker> assembleWorkerList) {
        this.threadId = threadId;
        this.assembleWorkerList = assembleWorkerList;
        this.assembleWorkerNum = assembleWorkerList.size();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("BodyWorker interrupted, threadId=" + threadId);
                System.out.println(e.getMessage());
            }
            int index = (int) (Math.random() * assembleWorkerNum);
            assembleWorkerList.get(index).addBody();
        }
    }
}

class WheelWorkerRunnable implements Runnable {
    private int threadId;

    private int assembleWorkerNum;

    private List<AssembleWorker> assembleWorkerList;

    public WheelWorkerRunnable(int threadId, List<AssembleWorker> assembleWorkerList) {
        this.threadId = threadId;
        this.assembleWorkerList = assembleWorkerList;
        this.assembleWorkerNum = assembleWorkerList.size();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("WheelWorker interrupted, threadId=" + threadId);
                System.out.println(e.getMessage());
            }
            int index = (int) (Math.random() * assembleWorkerNum);
            assembleWorkerList.get(index).addWheel();
        }
    }
}
