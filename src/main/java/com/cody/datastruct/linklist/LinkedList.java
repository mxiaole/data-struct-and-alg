package com.cody.datastruct.linklist;

/**
 * 链表数据结构
 */
public class LinkedList<E> {
    private class Node {
        public E e;
        public Node next;

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

    // 链表的成员变量
    private Node head; // 链表的头结点
    private int size; // 链表中元素的个数

    // 链表的构造函数
    public LinkedList() {
        head = null;
        size = 0;
    }

    // 获取链表中元素的个数
    public int getSize() {
        return size;
    }

    // 判断链表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 向链表中添加元素, 在链表头添加元素
    public void addFirst(E e) {
        Node node = new Node(e);
        node.next = head;
        head = node;

        // 上述三行代码可以使用下面的一行
        // head = new Node(e, head);

        size++;
    }

    // 在链表的中间添加元素
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("invalid index");
        }

        // 如果index为0表示在链表的头添加节点
        if (index == 0) {
            addFirst(e);
        } else {
            Node pre = head;
            for (int i = 0; i < index - 1; i++) {
                pre = pre.next;
            }

            Node node = new Node(e);
            node.next = pre.next;
            pre.next = node;

            // 上面的三行代码可以使用下面的一行实现
            // pre.next = new Node(e, pre.next);
            size++;
        }
    }

    // 向链表的末尾添加元素
    public void addLast(E e) {
        add(size, e);
    }

    // 从链表中删除元素
    public E remove(int index) {
        // 如果要删除的元素是头结点
        E ret;
        if (index == 0) {
            System.out.println(head);
            ret = head.e;
            head = head.next;
        } else {
            Node prev = head;
            for (int i = 0; i < index; i++) {
                prev = prev.next;
            }
            Node delNode = prev.next;
            ret = delNode.e;
            prev.next = delNode.next;
            delNode.next = null;
        }
        size--;
        return ret;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        while (head != null) {
            stringBuilder.append(head.e + "->");
            head = head.next;
        }

        stringBuilder.append("null");

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addFirst(1);
        linkedList.addFirst(2);
        linkedList.addFirst(3);
        linkedList.addFirst(4);
        linkedList.addFirst(5);

//        System.out.println(linkedList);


        // 删除元素
        linkedList.remove(2);
        System.out.println(linkedList);
    }
}

