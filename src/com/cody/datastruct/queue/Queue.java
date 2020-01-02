package com.cody.datastruct.queue;

public interface Queue<E> {
    void enqueue(E e);  // 向队列中添加一个元素

    E dequeue();  // 从队列中取出一个元素

    E getFront();  // 获取队首的元素

    int getSize();  // 获取队列中元素的个数

    boolean isEmpty();  // 判断栈是否为空
}
