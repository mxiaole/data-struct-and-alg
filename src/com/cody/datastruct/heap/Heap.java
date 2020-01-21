package com.cody.datastruct.heap;

import com.cody.datastruct.array.DynamicArray;

/**
 * 基于动态数组实现最大堆
 * 最大堆：
 * 1. 最大堆是一个完全二叉树
 * 2. 使用数组实现最大堆:
 * 假设一个节点在数组中对应的下标是i，
 * 那么这个节点的左孩子的下标为2*i, 右孩子在数组中的下标为2*i + 1
 * 父节点在数组中的下标是i/2
 */

public class Heap<E extends Comparable<E>> {
    // 堆中的私用成员
    private DynamicArray<E> maxHeap;

    // 堆对外提供的方法
    // 1. 构造函数
    public Heap() {
        maxHeap = new DynamicArray<>();
    }

    // 2. 返回堆中的元素个数
    public int size() {
        return maxHeap.getSize();
    }

    // 3. 判断堆是否为空
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    // 4. 堆的辅助私有方法
    /*获取数组下标为i的节点的父节点数组下标*/
    private int parent(int i) {
        if (i == 0) {
            throw new IllegalArgumentException("invalid index");
        }
        return (i - 1) / 2;
    }

    /*获取数组下标为i的节点的左孩子数组下标*/
    private int left(int i) {
        return i * 2 + 1;
    }

    /*获取数组下标为i的节点的右孩子数组下标*/
    private int right(int i) {
        return i * 2 + 2;
    }

    // 5. 向堆中添加元素
    public void add(E e) {
        // 在数组的末尾添加元素
        maxHeap.addLast(e);
        // 然后将这个元素进行上浮操作
        siftUp(maxHeap.getSize() - 1);
    }

    private void siftUp(int k) {
        while (k > 0 && maxHeap.get(parent(k)).compareTo(maxHeap.get(k)) < 0) {
            maxHeap.swap(k, parent(k));
            k = parent(k);
        }
    }

    // 6. 看对堆中的最大元素
    public E findMax() {
        if (maxHeap.getSize() == 0) {
            throw new IllegalArgumentException("the maxHeap is empty");
        }

        return maxHeap.get(0);
    }

    // 7. 取出堆的最大元素
    public E extractMax() {
        // 获取最大值
        E ret = findMax();
        // 最大的元素和最后一个元素进行交换
        maxHeap.swap(0, maxHeap.getSize() - 1);
        // 将最大的值从数组中移除
        maxHeap.removeLast();
        // 将数组0号索引的数据进行下沉
        siftDown(0);
        return ret;
    }

    private void siftDown(int k) {
        while (left(k) < maxHeap.getSize()) {
            // 查找k的左右孩子中的较大值
            int l = left(k);  // 左孩子的索引
            int r = l + 1;  // 右孩子的索引

            // 如果有右孩子，并且右孩子的值比左孩子的值大
            if (r < maxHeap.getSize() && maxHeap.get(r).compareTo(maxHeap.get(l)) > 0) {
                l = r;  // 此时l为左右孩子的最大值索引
            }

            // k索引的值和它的左右孩子中的最大值进行比较
            // 如果k索引对应的值大于它的左右孩子中的最大值，说明k索引对应的值不用进行￿下沉
            if (maxHeap.get(k).compareTo(maxHeap.get(l)) >= 0) {
                break;
            }

            // 否则，需要进行下沉
            maxHeap.swap(k, l);
            k = l;
        }
    }
}
