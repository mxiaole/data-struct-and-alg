package main.java.com.cody.datastruct.segmenttree;

/**
 * 线段树
 * 1. 实现：使用数组进行数据对象的存储
 * 2. 线段树中的每个节点存储的是一个区间中的信息
 */
class SegmentTree<E> {

    private E[] tree; // 实际实现线段树的数组
    private E[] data; // 存储数组的副本

    private Merger<E> merger;  // 用户自定义的的合并左右子树的节点数据的合成器

    /**
     * 构造函数, 将一个传入的数组进行创建线段树
     */
    public SegmentTree(E[] arr, Merger<E> merger) {
        // 合成器
        this.merger = merger;

        // 创建数组的备份
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }

        tree = (E[]) new Object[4 * arr.length];

        // 创建线段树
        buildSegmentTree(0, 0, data.length - 1);
    }

    // 获取线段树中元素的个数
    public int getSize() {
        return data.length;
    }

    // 获取指定索引的元素
    public E get(int index) {
        if (index < 0 || index > data.length) {
            throw new IllegalArgumentException("index error");
        }
        return data[index];
    }

    // 返回完全二叉树的数组表示中，索引index的左孩子的索引
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    // 返回index的右孩子的索引
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    // 将一个数组，组织成一个线段树
    // 在treeIndex的位置创建表示区间[l....r]的线段树, l和r表示的是
    // treeIndex表示当前创建的线段树的根节点, 对应的节点
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        int mid = l + (r - l) / 2;

        // 创建左子树的线段树
        buildSegmentTree(leftTreeIndex, l, mid);
        // 创建右子树的线段树
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        // 综合左右子树，确定父节点的值
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    // 查询线段树中的区间[queryL, queryR]
    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL > data.length || queryR < 0 || queryR > data.length || queryL > queryR) {
            throw new IllegalArgumentException("index error");
        }

        return query(0, 0, data.length - 1, queryL, queryR);
    }

    // 在以treeIndex为根的线段树中[l, r]的范围里，搜索区间[queryL...queryR]的值
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (queryL >= mid + 1) {
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {
            return query(leftTreeIndex, l, mid, queryL, queryR);
        }

        E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        return merger.merge(leftResult, rightResult);
    }

    // 更新index位置的元素
    public void set(int index, E e) {
        if (index < 0 || index > data.length) {
            throw new IllegalArgumentException("invalid index");
        }

        data[index] = e; // 修改副本中的元素为e

        // 修改线段树中的数值为e
        set(0, 0, data.length - 1, index, e);
    }

    // 更新以treeIndex为根的线段树中更新index的值为e
    private void set(int treeIndex, int l, int r, int index, E e) {
        if (l == r) {
            tree[treeIndex] = e;
        }

        int mid = l + (r - l) / 2;

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (index >= mid + 1)
            set(rightTreeIndex, mid + 1, r, index, e);
        else
            set(leftTreeIndex, l, mid, index, e);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('[');
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");

            if (i != tree.length - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }

    public static void main(String[] args) {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, new Merger<Integer>() {
            @Override
            public Integer merge(Integer a, Integer b) {
                return a + b;
            }
        });

        System.out.println(segmentTree);
    }
}