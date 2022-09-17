package com.cody.datastruct.stack;

import com.cody.datastruct.linklist.LinkedListWithDummyHead;

/**
 * 使用链表实现栈
 */

public class LinkedListStack<E> implements Stack<E> {

    // 成员属性
    private LinkedListWithDummyHead<E> stack;

    // 构造函数
    public LinkedListStack() {
        stack = new LinkedListWithDummyHead<>();
    }

    @Override
    public void push(E e) {
        stack.addFirst(e);
    }

    @Override
    public E pop() {
        return stack.remove(0);
    }

    @Override
    public E peek() {
        return stack.getFirst();
    }

    @Override
    public int getSize() {
        return stack.getSize();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("stack top: ");
        sb.append(stack);

        return sb.toString();
    }

    public static void main(String[] args) {
        LinkedListStack<Integer> stack = new LinkedListStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        System.out.println(stack);

        stack.pop();
        System.out.println(stack);

    }

}