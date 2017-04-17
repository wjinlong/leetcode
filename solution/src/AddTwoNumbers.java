/**
 * @author : King
 *         date : 2017/4/17 0017 17:39
 *         Created by
 *         IntelliJ IDEA 2016.2.5
 *         Build #IU-162.2228.15, built on October 14, 2016
 *         JRE: 1.8.0_91-b14 amd64
 *         JVM: Java HotSpot(TM) 64-Bit Server VM by Oracle Corporation
 */

/*
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8

 */
class ListNode {
    int val;
    ListNode next = null;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class AddTwoNumbers {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        System.out.print("l1:");
        printList(l1);
        System.out.println();

        System.out.print("l2:");
        printList(l2);
        System.out.println();


        ListNode l3 = addTwoNumbers2(l1, l2);

        System.out.print("l1+l2:");
        printList(l3);
        System.out.println();
    }

    /*
    正常思路版，类似两个链表的合并
     */
    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        //最前面的节点
        ListNode head = new ListNode(-1);
        ListNode tmp = head;

        int val;
        int carry = 0;

        while (l1 != null && l2 != null) {
            val = (l1.val + l2.val + carry) % 10;
            carry = (l1.val + l2.val + carry) / 10;
            tmp.next = new ListNode(val);
            l1 = l1.next;
            l2 = l2.next;
            tmp = tmp.next;

        }
        while (l1 != null) {
            //考虑连续进位，如 1+99；
            tmp.next = new ListNode((l1.val + carry) % 10);
            carry = (l1.val + carry) / 10;
            l1 = l1.next;
            tmp = tmp.next;
        }
        while (l2 != null) {
            tmp.next = new ListNode((l2.val + carry) % 10);
            carry = (l2.val + carry) / 10;
            l2 = l2.next;
            tmp = tmp.next;
        }
        //考虑最后进位，如 5+5；
        if (carry != 0) {
            tmp.next = new ListNode(carry);
        }
        return head.next;
    }

    /*
    leetcode 大神精简版
     */
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode prev = new ListNode(0);
        ListNode head = prev;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            ListNode cur = new ListNode(0);
            int sum = ((l1 == null) ? 0 : l1.val) + ((l2 == null) ? 0 : l2.val) + carry;
            cur.val = sum % 10;
            carry = sum / 10;
            prev.next = cur;
            prev = cur;

            l1 = (l1 == null) ? l1 : l1.next;
            l2 = (l2 == null) ? l2 : l2.next;
            cur = cur.next;
        }
        return head.next;
    }

    /**
     * 打印链表
     *
     * @param l 链表的头
     */
    public static void printList(ListNode l) {
        if (l != null) {
            System.out.print(l.val);
            l = l.next;
        }
        while (l != null) {
            System.out.print("->" + l.val);
            l = l.next;
        }
    }
}
