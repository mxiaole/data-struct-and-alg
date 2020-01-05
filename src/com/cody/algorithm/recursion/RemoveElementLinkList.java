package com.cody.algorithm.recursion;

/**
 * 使用递归算法删除链表中的指定的元素
 */

public class RemoveElementLinkList {
    public class ListNode {
        private int value;
        private ListNode next;

        public ListNode(int value) {
            this.value = value;
        }
    }

    public ListNode remove(ListNode head, int value) {
        // 最基本问题
        if (head == null) {
            return head;
        }
        // 原问题转换为更小的问题
        ListNode res = remove(head.next, value);
        if (head.value == value) {
            return res;
        } else {
            head.next = res;
            return head;
        }
        // 上述代码可以使用下面的两行行代码代替
        // head.next = remove(head.next, value);
        // return head.value == value? head : head.next;
    }

}