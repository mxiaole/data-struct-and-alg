package com.cody.datastruct.set;

import com.cody.datastruct.linklist.LinkedListWithDummyHead;

/**
 * 使用链表实现集合
 *
 * @param <E>
 */

public class LinkedListSet<E> implements Set<E> {
    private LinkedListWithDummyHead<E> set;

    public LinkedListSet() {
        set = new LinkedListWithDummyHead<>();
    }

    @Override
    public void add(E e) {
        if (!set.contains(e)) {

            set.addFirst(e);
        }
    }

    @Override
    public boolean contains(E e) {
        return set.contains(e);
    }

    @Override
    public void remove(E e) {
        set.removeElement(e);
    }

    @Override
    public int getSize() {
        return set.getSize();
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }
}
