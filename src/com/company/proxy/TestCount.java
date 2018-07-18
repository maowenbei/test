/**
 * @(#)TestCount.java, 2018-05-18.
 * <p>
 * Copyright 2018 Youdao, Inc. All rights reserved.
 * YOUDAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.company.proxy;

/**
 * TestCount
 *
 * @author Mao Wenbei
 *
 */
public class TestCount {

    public static void main(String[] args) {

        // 静态代理，每一个代理类只能为一个接口服务
        CountStaticProxy countProxy = new CountStaticProxy(new CountImpl());
        countProxy.updateCount();
        countProxy.queryCount();

        // 动态代理，只要写一个MyDynamicProxy，可以代理多个接口
        MyDynamicProxy myDynamicProxy = new MyDynamicProxy();
        Count count = (Count) myDynamicProxy.bind(new CountImpl());
        count.updateCount();
        count.queryCount();

        Book book = (Book) myDynamicProxy.bind(new BookImpl());
        book.readBook();

    }
}
