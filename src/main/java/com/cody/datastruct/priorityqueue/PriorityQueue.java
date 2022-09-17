package com.cody.datastruct.priorityqueue;

import com.cody.datastruct.heap.Heap;
import com.cody.datastruct.queue.Queue;

/**
 * 优先级队列的实现：使用最大堆进行实现
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {
    // 成员变量
    private Heap<E> queue;

    public PriorityQueue() {
        this.queue = new Heap<>();
    }

    // 成员方法
    @Override
    public int getSize() {
        return queue.size();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public E getFront() {
        return queue.findMax();
    }

    // 入队操作
    @Override
    public void enqueue(E e) {
        queue.add(e);
    }

    // 出队操作
    @Override
    public E dequeue() {
        return queue.extractMax();
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(5);
        queue.enqueue(4);
        int ret = queue.getFront();
        System.out.println(ret);
    }

}