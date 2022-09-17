package main.java.com.cody.datastruct.tree;

// 红黑树实现
public class BlackRedTree<K extends Comparable<K>, V> {
    private static final boolean RED = true, BLACK = false;

    // 定义红黑树中的节点
    private class Node {
        // 节点中数据的保存
        public K key;
        public V value;
        // 节点颜色
        public boolean color; // 是否是红色
        // 节点的左右孩子
        public Node left;
        public Node right;

        // 节点的构造函数
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            color = RED; // 新创建的节点默认为红色
        }
    }

    // 定义红黑树
    private Node root; // 红黑树的根
    private int size; // 红黑树中节点的个数

    // 基本操作
    // 1. 添加元素
    public void add(K key, V value) {
        root = add(root, key, value);
        // 保持根节点为黑色
        root.color = BLACK;
        // 根据添加的节点的位置不同，需要进行不同的逻辑处理

    }

    // 添加节点的辅助操作
    private Node add(Node root, K key, V value) {
        if (root == null) { // 向一个空树中添加一个节点
            size++;
            return new Node(key, value);
        }
        if (key.compareTo(root.key) < 0) { // 根节点的左孩子中添加节点
            root.left = add(root.left, key, value);
        } else if (key.compareTo(root.key) > 0) { // 根节点的右孩子中添加节点
            root.right = add(root.right, key, value);
        } else { // 添加的节点已经存在
            root.value = value;
        }

        // 红黑树性质的维护操作
        // 1. 是否需要进行左旋操作
        if (isRed(root.right) && !isRed(root.left)) {
            root = leftRotate(root);
        }
        // 2. 判断是否需要右旋转
        if (isRed(root.left) && isRed(root.left.left)) {
            root = rightRotate(root);
        }
        // 3. 判断是否需要进行颜色的翻转
        if (isRed(root.left) && isRed(root.right)) {
            flipColors(root);
        }

        return root;
    }

    // 添加节点的辅助函数：左旋转
    // node为根的树进行左旋转, 返回旋转了之后的树的根
    private Node leftRotate(Node node) {
        Node x = node.right;
        node.right = x.left;
        x.left = node;

        // 维持节点颜色
        x.color = node.color;
        node.color = RED;

        return x;
    }

    private Node rightRotate(Node root) {
        Node x = root.left;
        root.left = x.right;
        x.right = root;

        x.color = root.color;
        root.color = RED;

        return x;
    }

    // 辅助操作,颜色翻转
    private void flipColors(Node root) {
        root.color = RED;
        root.left.color = BLACK;
        root.right.color = BLACK;
    }

    // 辅助操作：判断一个节点是不是红色节点
    private boolean isRed(Node node) {
        return node.color;
    }
}
