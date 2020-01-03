package com.cody.datastruct.linklist;

/**
 * 带有虚拟头结点的链表
 */
public class LinkedListWithDummyHead<E> {
    private class Node {
        public E e;
        public Node next;

        // 构造函数
        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    // 带有虚拟头结点的链表的成员属性
    private Node dummyHead;
    private int size;

    public LinkedListWithDummyHead() {
        dummyHead = new Node(null, null);
        size = 0;
    }

    // 获取链表的元素个数
    public int getSize() {
        return size;
    }

    // 判断链表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 向链表指定的位置添加元素
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("invalid index");
        }
        Node pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }

        Node node = new Node(e);
        node.next = pre.next;
        pre.next = node;
    }

    // 向链表头中添加元素
    public void addFirst(E e) {
        add(0, e);
    }

    // 向链表的结尾添加元素
    public void addLast(E e) {
        add(size, e);
    }
}
