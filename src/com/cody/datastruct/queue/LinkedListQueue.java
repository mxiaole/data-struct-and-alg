package com.cody.datastruct.queue;

/**
 * 使用链表实现队列
 */

public class LinkedListQueue<E> implements Queue<E> {
    // 定义节点类
    private class Node {
        // Node的成员属性
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

    // 栈的成员变量
    private Node head, tail;
    private int size;

    // 构造函数
    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void enqueue(E e) {
        // 从链表的尾进行入队
        if (tail == null) {  // 如果tail为空，说明head也为空，即链表中没有数据
            tail = new Node(e);
            head = tail;
        } else {
            tail.next = new Node(e);
            tail = tail.next;
        }

        size++;
    }

    @Override
    public E dequeue() {
        // 从链表的头进行出队列
        if (isEmpty()) {
            throw new IllegalArgumentException("queue is empty");
        }

        Node retNode = head;
        head = head.next;
        retNode.next = null;

        if (head == null) {  // 如果链表中只有一个元素
            tail = null;
        }

        size--;

        return retNode.e;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("queue is empty");
        }
        return head.e;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("queue: front ");
        Node cur = head;
        while (cur != null) {
            sb.append(cur + "->");
            cur = cur.next;
        }
        sb.append("null tail");

        return sb.toString();
    }

    public static void main(String[] args) {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        System.out.println(queue);
        System.out.println(queue.dequeue());
        System.out.println(queue);
    }
}
