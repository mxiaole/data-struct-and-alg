package com.cody.datastruct.array;


public class Array {
    private int[] data;
    private int size;

    // 构造函数
    public Array(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    // 无参构造，默认数组大小为10
    public Array() {
        this(10);
    }

    // 获取数组中元素的个数
    public int getSize() {
        return this.size;
    }

    // 获取数组的容量
    public int getCapacity() {
        return data.length;
    }

    // 返回数组是否是空
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * 数组的基本操作
     * 1. 向数组中添加元素 add()
     * 2. 从数组中删除元素 delete()
     * 3. 修改数组中的元素 update()
     * 3. 从数组中查找元素 search()
     * 4. 数组元素的遍历 foreach()
     */

    // 向数组的末尾添加元素
    public void addLast(int e) {
        // 添加元素的时候，首先判断是否有足够的空间
        if (size == data.length) {
            throw new IllegalArgumentException("array is full");
        }
        data[size] = e;
        size++;
    }

    // 向数组的头部添加元素
    public void addFirst(int e) {
        add(0, e);
    }

    // 向数组中指定的位置添加元素
    public void add(int index, int e) {
        if (size == data.length) {
            throw new IllegalArgumentException("array is full");
        }
        // 判断传入参数的合法性
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("array is full");
        }

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }


    // 获取指定索引的值
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index is illegal");
        }
        return data[index];
    }

    // 更新数组元素
    public void set(int index, int e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index is illegal");
        }
        data[index] = e;
    }

    // 判断元素e是不是在数组中
    public boolean contains(int e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return true;
            }
        }
        return false;
    }

    // 查找元素e的数组下标
    public int find(int e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return i;
            }
        }

        return -1;
    }

    // 从数组中删除元素, 返回删除的元素值
    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }
        int ret = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;

        return ret;
    }

    // 从数组中删除指定的元素e, 只删除一个
    public void removeElement(int e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array size is : %d, capacity is : %d\n", size, getCapacity()));

        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(", ");
            }
        }

        res.append("]");

        return res.toString();
    }

    public static void main(String[] args) {
        /**
         * 使用java自带的数组
         */
        /*
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        int[] scores = new int[]{1, 3, 4};
        for (int i = 0; i < scores.length; i++) {
            System.out.println(scores[i]);
        }

        for (int score : scores) {
            System.out.println(score);
        }
        */
        // 使用自定义的数组
        Array array = new Array(10);
        for (int i = 0; i < 10; i++) {
            array.addLast(i);
        }

        System.out.println(array);
    }

}
