/**
 * @(#)CountProxy.java, 2018-05-18.
 * <p>
 * Copyright 2018 Youdao, Inc. All rights reserved.
 * YOUDAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.company.proxy;

/**
 * 这是一个代理类（增强CountImpl实现类）
 * <p>
 * 静态代理
 *
 * @author Mao Wenbei
 */
public class CountStaticProxy implements Count {

    private CountImpl countImpl;

    public CountStaticProxy(CountImpl countImpl) {
        this.countImpl = countImpl;
    }

    @Override
    public void queryCount() {
        System.out.println("静态代理事务处理之前");
        // 调用委托类的方法;
        countImpl.queryCount();
        System.out.println("静态代理事务处理之后");
    }

    @Override
    public void updateCount() {
        System.out.println("静态代理事务处理之前");
        // 调用委托类的方法;
        countImpl.updateCount();
        System.out.println("静态代理事务处理之后");
    }
}
