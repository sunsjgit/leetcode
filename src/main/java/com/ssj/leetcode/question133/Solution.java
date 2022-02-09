package com.ssj.leetcode.question133;

import java.util.*;

/**
 * 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
 * <p>
 * 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
 * <p>
 * class Node {
 * public int val;
 * public List<Node> neighbors;
 * }
 *  
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/clone-graph
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node(int val) {
            this.val = val;
        }

        public Node(List<Node> neighbors) {
            this.neighbors = neighbors;
        }

        public Node(int val, List<Node> neighbors) {
            this.val = val;
            this.neighbors = neighbors;
        }

        public Node() {
        }
    }


    /**
     * 深度优先搜索
     *
     * @param node
     * @param visited
     * @return
     */
    public Node deepFirstSolution(Node node, HashMap<Node, Node> visited) {

        // 判断节点是否为空
        if (Objects.isNull(node)) {
            return node;
        }

        // 判断访问缓存中是否包含当前节点  若包含则直接返回
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // 创建clone node
        Node cloneNode = new Node(node.val, new ArrayList<Node>());

        // 若不包含 则计入缓存
        visited.put(node, cloneNode);

        // 遍历当前节点的相邻节点
        for (Node neighbor : node.neighbors) {
            // 添加Clone的相邻节点
            cloneNode.neighbors.add(deepFirstSolution(neighbor, visited));
        }

        return cloneNode;
    }

    public Node cloneGraph(Node node) {

        // 创建对应访问缓存
        HashMap<Node, Node> visited = new HashMap<>();
        // 深度优先搜索
        return deepFirstSolution(node, visited);
    }


    /**
     * 测试图例
     * <p>
     * <p>
     * <p>
     * 1 ------ 2
     * |        |
     * |        |
     * |        |
     * 4 ------ 3
     *
     * @param args
     */
    public static void main(String[] args) {

        // 构建图
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        // 2 4 与 1 相邻
        node1.neighbors = Arrays.asList(node2, node4);
        // 1 3 与 2 相邻
        node2.neighbors = Arrays.asList(node1, node3);
        // 2 4 与 3 相邻
        node3.neighbors = Arrays.asList(node2, node4);
        // 1 3 与 4 相邻
        node4.neighbors = Arrays.asList(node1, node3);

        // 执行克隆
        Solution solution = new Solution();
        Node node = solution.cloneGraph(node1);
        System.out.println(node);
    }
}


