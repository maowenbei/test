/**
 * @(#)BookImpl.java, 2018-05-18.
 * <p>
 * Copyright 2018 Youdao, Inc. All rights reserved.
 * YOUDAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.company.proxy;


/**
 * BookImpl
 *
 * @author Mao Wenbei
 *
 */
public class BookImpl implements Book {
    @Override
    public void readBook() {
        System.out.println("阅读图书方法。。。");
    }
}
