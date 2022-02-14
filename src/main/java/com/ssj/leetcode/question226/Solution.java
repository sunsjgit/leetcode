package com.ssj.leetcode.question226;

import com.ssj.util.TreeNode;
import com.ssj.util.TreeUtil;

/**
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/invert-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution {


    /**
     * 深度递归 从叶子节点开始替换
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        // 深度递归
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        // 翻转
        root.left = right;
        root.right = left;
        return root;
    }


    public static void main(String[] args) {

        // 构建树
        TreeNode tree = TreeUtil.createTree(new Integer[]{1, null, 2});


        Solution solution = new Solution();
        solution.invertTree(tree);
    }
}