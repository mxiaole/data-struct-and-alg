package com.cody.datastruct.tree;

import java.util.TreeMap;

/**
 * 字典树
 * 1. 字典树是一种用来处理字符串的数据结构
 * 2.
 */
public class Trie<E> {

    // 定义字典树中的节点
    private class Node {
        public boolean isWord;  // 从根节点到该节点是不是一个单词
        public TreeMap<Character, Node> next;  // 该节点的下一个节点的

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    // 定义字典树的成员变量
    private Node root;  // 字典树的根
    private int size;  // 字典树中存储的单词的数量

    // 字典树的构造函数
    public Trie() {
        root = new Node();
        size = 0;
    }

    // 字典树的成员方法

    // 获取trie中存储的单词的数量
    public int getSize() {
        return size;
    }

    // 向trie中添加一个新的单词
    public void add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            // 获取单词的字母
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }

        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    // 查询单词是否在trie中
    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }

        return cur.isWord;
    }

    // 查询是否有以prefix为前缀的
    public boolean isPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }

        return true;
    }

}
