package com.cody.datastruct.stack;

public interface Stack<E> {
    public void push(E e);  // 元素入栈

    public E pop();  // 元素出栈

    public E peek();  // 查看栈顶元素

    public int getSize();  // 获取栈中元素的数量

    public boolean isEmpty();  // 判断栈是否为空
}
