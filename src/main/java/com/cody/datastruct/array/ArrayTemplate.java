package com.cody.datastruct.array;


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
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }

        data[index] = e;
        size++;
    }

    // 判断元素e是否在数组中
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (e.equals(data[i])) {  // 对象值的比较
                return true;
            }
        }

        return false;
    }

    // 删除指定索引的元素
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index is invalid");
        }

        E ret = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        data[size] = null;  // 释放内存

        return ret;
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("Array size is : %d, capacity is : %d\n", size, getCapacity()));

        result.append('[');
        for (int i = 0; i < size; i++) {
            result.append(data[i] + ", ");
        }
        result.append(']');
        return result.toString();
    }

    public static void main(String[] args) {
        class Student {
            private String name;
            private int score;

            public Student(String name, int score) {
                this.name = name;
                this.score = score;
            }

            @Override
            public String toString() {
                return String.format("student name is : %s, score is : %d", this.name, this.score);
            }
        }

        Student student1 = new Student("zhangsan", 89);
        Student student2 = new Student("lisi", 90);
        Student student3 = new Student("wangwu", 91);

        ArrayTemplate<Student> studentArrayTemplate = new ArrayTemplate<>();
        studentArrayTemplate.addFirst(student1);
        studentArrayTemplate.addFirst(student2);
        studentArrayTemplate.addFirst(student3);

        System.out.println(studentArrayTemplate);

        ArrayTemplate<Integer> arrayTemplate = new ArrayTemplate<>();
        arrayTemplate.addFirst(10);
        System.out.println(arrayTemplate);

    }
}
