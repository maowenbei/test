/**
 * @(#)ListNode.java, 2018-06-06.
 * <p>
 * Copyright 2018 Youdao, Inc. All rights reserved.
 * YOUDAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.company.leetcode;

/**
 * ListNode
 *
 * @author Mao Wenbei
 *
 */

public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    String printList() {
        StringBuilder sb = new StringBuilder("[");
        sb.append(this.val);
        ListNode temp = this.next;
        while (temp != null) {
            sb.append(", ");
            sb.append(temp.val);
            temp = temp.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
