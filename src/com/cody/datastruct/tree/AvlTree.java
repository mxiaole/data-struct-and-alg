package com.cody.datastruct.tree;

import java.util.ArrayList;

public class AvlTree<K extends Comparable<K>, V> {
    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }
    }

    // avl树的数据结构
    private Node root;
    private int size;

    public AvlTree() {
        root = null;
        size = 0;
    }

    // 向avl中添加一个节点
    public void add(K key, V value) {
        root = add(root, key, value);
        this.size += 1;
    }

    // 添加节点add的辅助递归函数
    // root表示的是以root为根的树
    private Node add(Node root, K key, V value) {
        if (root == null) {
            return new Node(key, value);
        }

        if (key.compareTo(root.key) < 0) {
            root.left = add(root.left, key, value);
        } else if (key.compareTo(root.key) > 0) {
            root.right = add(root.right, key, value);
        } else {
            root.value = value;
        }

        // 维护root的高度
        root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;

        // 计算root为根的树的平衡因子, 判断是不是平衡
        int balanceFactor = getBalanceFactor(root);

        // 如果平衡破坏，是因为向root的左子树中添加了节点，对root节点使用右旋转维护平衡
        // LL
        if (balanceFactor > 1 && getBalanceFactor(root.left) >= 0) {
            return rightRotate(root);
        }

        // 如果平衡被破坏，是因为向root的右子树中添加了节点，root节点使用左旋转来维护平衡
        // RR
        if (balanceFactor < -1 && getBalanceFactor(root.right) <= 0) {
            return leftRotate(root);
        }

        // 如果是LR的情况， 转为LL
        if (balanceFactor > 1 && getBalanceFactor(root.left) < 0) {
            // 先转为LL, 对root.left进行左旋转
            root.left = leftRotate(root.left);
            // 然后root进行右旋
            return rightRotate(root);
        }
        // 如果是RL的情况, 先转为RR
        if (balanceFactor < -1 && getBalanceFactor(root.right) > 0) {
            // 转为RR
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    // 辅助函数，右旋操作
    private Node rightRotate(Node root) {
        Node x = root.left;
        Node t3 = x.right;

        x.right = root;
        root.left = t3;

        // 更新root节点的height值, root节点的高度值
        root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    // 辅助函数，左旋操作
    private Node leftRotate(Node root) {
        Node x = root.right;
        Node t3 = x.left;
        x.left = root;
        root.right = t3;

        // 更新height
        root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    // 辅助函数获取一个root为根的树的高度
    private int getHeight(Node root) {
        if (root == null) {
            return 0;
        }
        return root.height;
    }

    // 辅助函数，获取以root为根的树的平衡因子
    private int getBalanceFactor(Node root) {
        if (root == null) {
            return 0;
        }
        return getHeight(root.left) - getHeight(root.right);
    }

    // 辅助函数，判断以root为根的树是二分搜索树
    // 利用二分搜索树的中序遍历的结果是有序的这一个性质来进行判断
    public boolean isBst() {
        ArrayList<K> arrayList = new ArrayList<>();
        inOrder(root, arrayList);
        for (int i = 1; i < arrayList.size(); i++) {
            if (arrayList.get(i - 1).compareTo(arrayList.get(i)) > 0) {
                return false;
            }
        }

        return true;
    }

    // 辅助函数，中序遍历
    private void inOrder(Node root, ArrayList<K> arrayList) {
        if (root == null) {
            return;
        }
        inOrder(root.left, arrayList);
        arrayList.add(root.key);
        inOrder(root.right, arrayList);
    }

    // 判断以root为根的树是否是平衡二叉树
    public boolean isBalanced() {
        return isBalanced(root);
    }

    // 辅助函数，判断一个以root为根的树是否是平衡二叉树
    private boolean isBalanced(Node root) {
        if (root == null) {
            return true;
        }
        int balanceFactor = getBalanceFactor(root);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }

    // 删除avl树中的任意节点
    public void remove(K key) {
        remove(root, key);

    }

    // 删除节点的辅助函数
    private Node remove(Node root, K key) {
        if (root == null) {
            return null;
        }

        // 待删除的节点在root的左子树
        Node retNode;
        if (root.key.compareTo(key) < 0) {
            root.left = remove(root.left, key);
            retNode = root;
        } else if (root.key.compareTo(key) > 0) { // 待删除的节点在root的右子树
            root.right = remove(root.right, key);
            retNode = root;
        } else { // 当前的节点就是待删除的节点
            // 如果待删除的节点没有右孩子
            if (root.right == null) {
                size--;
                retNode = root.left;
            }
            // 如果待删除的节点没有左孩子
            else if (root.left == null) {
                size--;
                retNode = root.right;
            } else {

                // 如果待删除的节点既有左子树又有右子树
                // 从右子树中找到最小的值来替换该节点
                Node node = minimum(root.right);
                node.right = remove(root.right, node.key);
                node.left = root.left;
                node.left = node.right = null;
                retNode = node;
            }

            if (retNode == null) {
                return null;
            }

            // 判断retNode是否需要维护平衡
            // 更新retNode的height
            retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));
            // 判断retNode是否平衡
            int balanceFactor = getBalanceFactor(retNode);

            if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
                return rightRotate(retNode);
            }

            // 如果平衡被破坏，是因为向root的右子树中添加了节点，root节点使用左旋转来维护平衡
            // RR
            if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
                return leftRotate(retNode);
            }

            // 如果是LR的情况， 转为LL
            if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
                // 先转为LL, 对root.left进行左旋转
                retNode.left = leftRotate(retNode.left);
                // 然后root进行右旋
                return rightRotate(retNode);
            }
            // 如果是RL的情况, 先转为RR
            if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
                // 转为RR
                retNode.right = rightRotate(retNode.right);
                return leftRotate(retNode);
            }
        }

        return retNode;

    }

    // 查找node为根的树中左子树为空的节点
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    public static void main(String[] args) {
        AvlTree<Integer, String> tree = new AvlTree<>();
        tree.add(10, "hello");
        tree.add(20, "hello");
        tree.add(30, "hello");
        tree.add(40, "hello");
        tree.add(15, "hello");

        System.out.println(tree.isBalanced());
        System.out.println(tree.isBst());

        tree.remove(15);

        System.out.println(tree.isBalanced());
        System.out.println(tree.isBst());
    }
}
