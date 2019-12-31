package com.cody.datastruct.array;

import com.sun.crypto.provider.ARCFOURCipher;

// 使用泛型实现
public class ArrayTemplate<E> {
    private int size;
    private E[] data;

    // 定义一个有参构造
    public ArrayTemplate(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    // 定义一个无参构造
    public ArrayTemplate() {
        this(10);
    }

    // 获取数组的容量
    public int getCapacity() {
        return data.length;
    }

    // 获取数组的元素个数
    public int getSize() {
        return size;
    }

    // 在数组的首部添加元素
    public void addFirst(E e) {
        add(0, e);
    }

    // 向数组的指定位置添加一个元素
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index must be non-negative");
        }
        for (int i = size; i > index; i--) {
            data[i + 1] = data[i];
        }

        data[index] = e;
        size++;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("Array size is : %d, capacity is : %d\n", size, getCapacity()));

        result.append('[');
        for (int i = 0; i < size; i++) {
            result.append(data[i]);
        }
        result.append(']');
        return result.toString();
    }

    public static void main(String[] args) {
        ArrayTemplate<Integer> array = new ArrayTemplate<>();
        array.addFirst(3);

        System.out.println(array);
    }
}
