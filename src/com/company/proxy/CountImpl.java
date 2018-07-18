/**
 * @(#)CountImpl.java, 2018-05-18.
 * <p>
 * Copyright 2018 Youdao, Inc. All rights reserved.
 * YOUDAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.company.proxy;

/**
 * 委托类(包含业务逻辑)
 *
 * @author Mao Wenbei
 *
 */
public class CountImpl implements Count {

    @Override
    public void queryCount() {
        System.out.println("查看账户方法...");
    }

    @Override
    public void updateCount() {
        System.out.println("修改账户方法...");
    }
}
