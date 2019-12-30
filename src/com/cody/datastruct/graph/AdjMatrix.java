package com.cody.datastruct.array.graph;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * 邻接矩阵表示的图
 */

public class AdjMatrix {
    // 节点的个数
    private int V;
    // 边的个数
    private int E;
    // 邻接矩阵
    private int[][] adj;

    /**
     * 邻接矩阵表示图
     *
     * @param fileName: 创建图的文件
     */
    public AdjMatrix(String fileName) {
        // 从文件中读取数据构建图
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            this.V = scanner.nextInt();
            if (this.V < 0) {
                throw new IllegalArgumentException("V must be non-negative");
            }
            this.adj = new int[V][V];
            this.E = scanner.nextInt();
            if (this.E < 0) {
                throw new IllegalArgumentException("E must be non-negative");
            }
            for (int i = 0; i < E; i++) {
                int a = scanner.nextInt();
                validateVertex(a);
                int b = scanner.nextInt();
                validateVertex(b);
                if (a == b) {
                    throw new IllegalArgumentException("Self loop is detected");
                }
                adj[a][b] = 1;
                adj[b][a] = 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d\n", V, E));
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                sb.append(String.format("%d ", adj[i][j]));
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * 判断输入的顶点是否有效
     *
     * @param v
     */
    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is valid");
        }
    }

    /**
     * 判断两个顶点是否有边
     */

    public boolean hasEdge(int v, int w) {
        // 临界条件判断
        validateVertex(v);
        validateVertex(w);
        // 判断两个顶点是否有边
        return adj[v][w] == 1;
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
    public ArrayList<Integer> adj(int v) {
        validateVertex(v);
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (adj[v][i] == 1) {
                res.add(i);
            }

        }

        return res;
    }


    public static void main(String[] args) {
        AdjMatrix adjMatrix = new AdjMatrix("g.txt");
        System.out.print(adjMatrix);

        adjMatrix.validateVertex(3);
        System.out.println("边的个数: " + adjMatrix.E());
        System.out.println("顶点的个数: " + adjMatrix.V());

        int degree = adjMatrix.degree(2);
        System.out.println("2号节点的度: " + degree);
    }
}
