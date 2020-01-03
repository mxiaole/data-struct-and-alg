package com.cody.datastruct.queue;


public class LoopQueue<E> implements Queue<E> {
    private E[] data;
    private int front, tail;
    private int size;

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];  // 因为有一个空间要浪费，所以进行了+1操作
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    // 获取队列的容量
    public int getCapacity() {
        return data.length - 1;  // 在循环队列中故意浪费掉一个空间
    }

    // 判断队列是否为空
    @Override
    public boolean isEmpty() {
        return this.front == this.tail;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void enqueue(E e) {
        // 首先判断队列是不是满的
        if ((tail + 1) % data.length == front) {
            // 如果满了，进行扩容
            resize(getCapacity() * 2);
        }

        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        // 数据从旧的空间中拷贝到新的空间中
        for (int i = 0; i < size; i++) {
            newData[i] = data[(i + front) % data.length];  // 将
        }
        data = newData;
        front = 0;
        tail = size;
    }


    @Override
    public E dequeue() {
        // 判断队列是否为空
        if (isEmpty()) {
            throw new IllegalArgumentException("queue is empty");
        }
        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        // 进行缩容
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("queue is empty");
        }
        return data[front];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("front: [");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            sb.append(data[i] + " ");
        }

        sb.append("] tail");

        return sb.toString();
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LoopQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        System.out.println(queue);
        queue.dequeue();
        System.out.println(queue);

    }
}
