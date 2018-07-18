/**
 * @(#)ExtractVersion.java, 2018-07-10.
 * <p>
 * Copyright 2018 Youdao, Inc. All rights reserved.
 * YOUDAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.company.string;


/**
 * ExtractVersion
 *
 * @author Mao Wenbei
 *
 */
public class ExtractVersion {

    public static int compareMobileVersion(String v1, String v2) {

        String[] arr1 = extractVersion(v1);
        String[] arr2 = extractVersion(v2);
        for (int i = 0; i < arr1.length && i < arr2.length; i++) {
            int ret = Integer.valueOf(arr1[i]) - Integer.valueOf(arr2[i]);
            if (ret != 0) {
                return ret;
            }
        }
        return arr1.length - arr2.length;
    }

    public static String[] extractVersion(String v) {
        String[] result = {"0", "0", "0", "0",};
        if (!v.contains("\\.") && v.length() > 10) {
            try {
                // PC版本号（例如ClientVer=60500000000）对应的版本号的格式是nn.nn.nnnn.nnnn
                long version = Long.parseLong(v);
                result[3] = String.valueOf(version % 10000);
                version /= 10000;
                result[2] = String.valueOf(version % 10000);
                version /= 10000;
                result[1] = String.valueOf(version % 100);
                version /= 100;
                result[0] = String.valueOf(version);
                return result;
            } catch (NumberFormatException e) {
            }
        }
        String[] arr = v.split("\\.");
        for (int i=0 ; i<arr.length ; i++) {
            result[i] = arr[i];
        }
        return result;
    }

    public static void main(String[] args) {

        System.out.println(compareMobileVersion("6.3", "5.3") );
        System.out.println(compareMobileVersion("6.3", "6.3") );
        System.out.println(compareMobileVersion("6.3", "7.3") );
        System.out.println(compareMobileVersion("60500000000", "6.3") );
        System.out.println(compareMobileVersion("60500000000", "6.5") );
        System.out.println(compareMobileVersion("60500000000", "7.3") );
        System.out.println(compareMobileVersion("10000000000", "7.3") );
    }
}
