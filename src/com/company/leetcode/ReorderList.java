/**
 * @(#)ReorderList.java, 2018-06-06.
 * <p>
 * Leetcode 143
 */
package com.company.leetcode;

/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * <p>
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 * <p>
 * Example 1:
 * <p>
 * Given 1->2->3->4, reorder it to 1->4->2->3.
 * Example 2:
 * <p>
 * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 *
 * @author Mao Wenbei
 */
public class ReorderList {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode latterHalf = findLatterHalf(head);
        latterHalf = reverse(latterHalf);

        System.out.println(head.printList());
        System.out.println(latterHalf.printList());

        while (latterHalf != null) {
            ListNode temp = head.next;
            head.next = latterHalf;
            latterHalf = latterHalf.next;
            head.next.next = temp;
            head = temp;
        }
    }

    public ListNode findLatterHalf(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode step1 = head;
        ListNode step2 = head.next;
        while (step2 != null && step2.next != null) {
            step1 = step1.next;
            step2 = step2.next.next;
        }
        ListNode result = step1.next;
        step1.next = null;
        return result;
    }

    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        ListNode next = head.next;
        cur.next = null;
        while (next != null) {
            ListNode temp = next;
            next = next.next;
            temp.next = cur;
            cur = temp;
        }
        return cur;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode cur = head;
        cur.next = new ListNode(2);
        cur = cur.next;
        cur.next = new ListNode(3);
//        cur = cur.next;
//        cur.next = new ListNode(4);
//        cur = cur.next;
//        cur.next = new ListNode(5);
//        cur = cur.next;
//        cur.next = new ListNode(6);
//        cur = cur.next;
//        cur.next = new ListNode(7);

        new ReorderList().reorderList(head);
        System.out.println(head.printList());
    }

}
