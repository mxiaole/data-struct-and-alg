package com.cody.datastruct.set;

import com.cody.datastruct.tree.BST;

public class BSTSet<E extends Comparable<E>> implements Set<E> {
    private BST<E> set;

    public BSTSet() {
        set = new BST<>();
    }

    @Override
    public void add(E e) {
        set.add(e);
    }

    @Override
    public boolean contains(E e) {
        return set.contains(e);
    }

    @Override
    public void remove(E e) {
        set.remove(e);
    }

    @Override
    public int getSize() {
        return set.size();
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }
}
