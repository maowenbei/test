/**
 * @(#)AddTwoNumbers.java, 2018-06-06.
 * <p>
 * Leetcode 2
 */
package com.company.leetcode;

/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Example
 * <p>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 *
 * @author Mao Wenbei
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
public class AddTwoNumbers {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode head = new ListNode(l1.val + l2.val);
        ListNode cur = head;
        while (l1.next != null || l2.next != null) {
            ListNode temp = new ListNode(0);
            cur.next = temp;
            if (cur.val >= 10) {
                temp.val ++;
                cur.val -= 10;
            }
            if (l1.next != null) {
                l1 = l1.next;
                temp.val += l1.val;
            }
            if (l2.next != null) {
                l2 = l2.next;
                temp.val += l2.val;
            }
            cur = temp;
        }
        if (cur.val >= 10){
            ListNode temp = new ListNode(1);
            cur.next = temp;
            cur.val -= 10;
        }
        return head;
    }

    /**
     * 这个方法更快一点
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode cur = l1;
        while (true) {
            cur.val += l2.val;
            if (l2.next == null) {
                break;
            }
            if (cur.next == null) {
                cur.next = l2.next;
                break;
            }
            l2 = l2.next;
            if (cur.val > 9) {
                cur.val -= 10;
                cur = cur.next;
                cur.val ++;
            } else {
                cur = cur.next;
            }
        }
        while(cur.val > 9) {
            if (cur.next == null) {
                cur.next = new ListNode(1);
            } else {
                cur.next.val ++;
            }
            cur.val -= 10;
            cur = cur.next;
        }
        return l1;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

//        ListNode l1 = new ListNode(9);
//        l1.next = new ListNode(8);
//        ListNode l2 = new ListNode(1);

//        ListNode l1 = new ListNode(1);
//        ListNode l2 = new ListNode(9);
//        l2.next = new ListNode(9);

        ListNode result = AddTwoNumbers.addTwoNumbers2(l1, l2);
        System.out.println(result.printList());
    }
}
