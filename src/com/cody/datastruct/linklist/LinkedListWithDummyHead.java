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

        size++;
    }

    // 向链表头中添加元素
    public void addFirst(E e) {
        add(0, e);
    }

    // 向链表的结尾添加元素
    public void addLast(E e) {
        add(size, e);
    }

    // 获取链表中index的元素
    public E get(int index) {
        // 判断index的合法性
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("invalide index");
        }
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    // 获取链表的第一个元素
    public E getFirst() {
        return get(0);
    }

    // 获取链表的最后一个元素
    public E getLast() {
        return get(size);
    }

    // 修改链表的第index元素
    public void set(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("invalid index");
        }
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;

    }

    // 查找链表中是否存在元素e
    public boolean contains(E e) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }

        return false;
    }

    // 从链表中删除元素
    public E remove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("invalid index");
        }
        Node pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        Node delNode = pre.next;
        pre.next = delNode.next;
        delNode.next = null;
        size--;

        return delNode.e;
    }

    // 删除链表中的指定元素
    public void removeElement(E e) {
        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.e.equals(e))
                break;
            prev = prev.next;
        }

        if (prev.next != null) {
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size--;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node cur = dummyHead.next;
        while (cur != null) {
            sb.append(cur + "->");
            cur = cur.next;
        }
        sb.append("null");

        return sb.toString();
    }

    public static void main(String[] args) {
        LinkedListWithDummyHead<Integer> linkedListWithDummyHead = new LinkedListWithDummyHead<>();
        linkedListWithDummyHead.add(0, 1);
        linkedListWithDummyHead.add(0, 2);
        linkedListWithDummyHead.add(0, 3);

        System.out.println(linkedListWithDummyHead);

        linkedListWithDummyHead.add(2, 666);
        System.out.println(linkedListWithDummyHead);
        linkedListWithDummyHead.remove(2);
        System.out.println(linkedListWithDummyHead);

    }
}