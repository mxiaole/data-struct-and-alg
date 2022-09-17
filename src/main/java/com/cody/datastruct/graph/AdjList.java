package com.cody.datastruct.graph;

import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 邻接表表示图
 */

public class AdjList {
    private int V;
    private int E;
    private LinkedList<Integer>[] adj;

    /**
     * 创建图
     */
    public AdjList(String fileName) {
        File file = new File(fileName);

        try {
            Scanner scanner = new Scanner(file);
            // 读取图的顶点个数
            this.V = scanner.nextInt();
            if (this.V < 0)
                throw new IllegalArgumentException("V must be non-negative");
            adj = new LinkedList[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new LinkedList<Integer>();
            }

            // 读取图的边的个数
            this.E = scanner.nextInt();
            if (this.E < 0)
                throw new IllegalArgumentException("E must be non-negative");

            // 创建邻接表
            for (int i = 0; i < E; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();

                adj[a].add(b);
                adj[b].add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断顶点是否有效
     */
    public void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + "is invalid");
    }

    /**
     * 判断两个顶点是否有边
     */
    public boolean hasEdge(int v, int w) {
        // 临界条件判断
        validateVertex(v);
        validateVertex(w);
        // 判断两个顶点是否有边
        return adj[v].contains(w);
    }

    /**
     * 获取节点的个数
     */
    public int V() {
        return V;
    }

    /**
     * 获取边的个数
     */
    public int E() {
        return E;
    }

    /**
     * 获取节点的度
     */
    public int degree(int v) {
        return adj(v).size();
    }

    /**
     * 获取一个顶点的所有的边
     */
    public LinkedList<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d\n", V, E));
        for (int v = 0; v < V; v++) {
            sb.append(String.format("%d : ", v));
            for (int w : adj[v])
                sb.append(String.format("%d ", w));
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        AdjList adjList = new AdjList("g.txt");
        System.out.println(adjList);
    }
}
