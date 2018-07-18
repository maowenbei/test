/**
 * @(#)TestEqual.java, 2018-04-12.
 * <p>
 * Copyright 2018 Youdao, Inc. All rights reserved.
 * YOUDAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.company.string;

/**
 * TestEqual
 *
 * @author Mao Wenbei
 */
public class TestEqual {

    public static void main(String[] args) {
        TestEqual testEqual = new TestEqual();
        testEqual.testStringEqual();
    }

    public void testStringEqual() {
        /**
         *
         * 在java中是比较引用的，即在内存中的地址。
         * 这种方式，java首先会在缓冲区查找是否有"java"这个常量对象，有就直接将其地址赋给s1，没有就创建一个"java"，然后将其赋给s1;
         * 然后对于s2java同样会在缓冲区中查找"java"，这次能查找到了，因为s1创建了一个"java",所以会将其地址赋给s2，如此，s1和s2便有了相同的地址。
         */
        String s1 = "java";
        String s2 = "java";
        System.out.println(s1.equals(s2));
        System.out.println(s1 == s2);

        /**
         * String s3 = new String("String");会直接在内存中开辟一个空间存储一个"String"，并讲引用赋给s3；
         * 同样 String s4 = new String("String");也会开辟一个空间，降低至给s4；
         * 所以s3和s4的地址不一样。
         */
        String s3 = new String("String");
        String s4 = new String("String");
        System.out.println(s3.equals(s4));
        System.out.println(s3 == s4);

        String s = "ja";
        String s5 = s + "va";
        String s6 = "java";
        System.out.println(s5.equals(s6));
        System.out.println(s5 == s6);

        /**
         * 查看String的equals()的源代码，其工作原理：
         * 1、首先比较引用，如果引用相同，返回true；
         * 2、比较类型，如果不是比较的不是String对象，返回false；
         * 3、比较长度，字符串长度不等时，返回false；
         * 4、逐个字符比较两个字符串，遇到不一样的字符，返回false；
         * 5、到最后都一致，返回ture；
         */
    }
}
