package com.ssj.util;

/**
 * @Author 82565
 * @Description
 * @since 2022/2/14 9:49
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public int index;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, int index) {
        this.val = val;
        this.index = index;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
