package com.cody.datastruct.hash;

import java.util.TreeMap;

// 哈希表的实现
public class HashTable<K, V> {
    private TreeMap<K, V>[] data; // 实际存放数据的数组
    private int M; // 数组的大小, M应该是一个合适的素数
    private int size; // 数组中实际存放的元素的个数

    public HashTable(int M) {
        this.M = M;
        this.size = 0;
        this.data = new TreeMap[M];
        for (int i = 0; i < M; i++) {
            data[i] = new TreeMap<>();
        }
    }

    public HashTable() {
        this(97);
    }

    // 定义一个hash函数，输入key，输出index
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;  // 调用java的hashcode来计算hash值
    }

    // 获取hash表中元素的个数
    public int getSize() {
        return this.size;
    }

    // 向hash表中添加数据
    public void add(K key, V value) {
        // 先获取key的index
        int index = hash(key);
        // 获取index对应的值
        TreeMap<K, V> map = data[index];
        // 判断map中是否否已经存在key
        if (map.containsKey(key)) {  // 如果存在key，就修改key对应的value
            map.put(key, value);
        } else {
            // 将数据放入index索引对应的数组中
            map.put(key, value);
            size++;
        }

    }

    // 删除key所对应的值
    public V remove(K key) {
        int index = hash(key);
        TreeMap<K, V> map = data[index];
        V value = map.remove(key);
        if (value == null) {
            return null;
        }

        size--;
        return value;
    }

    // 修改key对应的value
    public void set(K key, V value) {
        TreeMap<K, V> map = data[hash(key)];

        if (!map.containsKey(key)) {
            throw new IllegalArgumentException("key is not exist");
        }

        map.put(key, value);
    }

    // 判断key是否存在
    public boolean contains(K key) {
        return data[hash(key)].containsKey(key);
    }

    // 获取key对应的value
    public V get(K key) {
        return data[hash(key)].get(key);
    }

}
