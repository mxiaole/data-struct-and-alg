package com.cody.datastruct.stack;

import com.cody.datastruct.array.DynamicArray;

public class ArrayStack<E> implements Stack<E> {
    private DynamicArray<E> array;

    // 构造函数
    public ArrayStack(int capacity) {
        array = new DynamicArray<>(capacity);
    }

    public ArrayStack() {
        array = new DynamicArray<>();
    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Stack: ");
        stringBuilder.append("[");
        for (int i = 0; i < array.getSize(); i++) {
            stringBuilder.append(array.get(i) + " ");
        }
        stringBuilder.append("] top");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new ArrayStack<>();
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println(stack);
        int res = stack.pop();
        System.out.println(res);
        System.out.println(stack);
    }
}
