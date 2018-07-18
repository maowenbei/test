/**
 * @(#)TestSizeOf.java, 2018-04-12.
 * <p>
 * Copyright 2018 Youdao, Inc. All rights reserved.
 * YOUDAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.company.string;

/**
 * TestExtend
 *
 * @author Mao Wenbei
 *
 */
public class TestExtend {

    public static void main(String[] args){
        S s=new S();
        s.printValue();
        A as=(A)s;
        as.printValue();
        as=new A();
        as.printValue();
    }
}

class A{
    public void printValue(){
        System.out.print("A");
    }
}

class S extends A{
    public void printValue(){
        System.out.print("S");
    }
}
