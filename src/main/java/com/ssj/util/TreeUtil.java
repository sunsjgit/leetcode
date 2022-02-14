package com.ssj.util;

/**
 * @Author 82565
 * @Description 构建树
 * @since 2022/2/14 8:59
 */
public class TreeUtil {


    /**
     * 根据数组构建树
     * <p>
     * 例如 [1,2,3,4,5,6, 7]
     * <p>
     * *               1
     * *        2             3
     * *    4       5       6       7
     * * 8    9  10  11  12  13  14  15
     * <p>
     *
     * @param nums 数组
     * @param node 开始节点
     */
    public static TreeNode createTree(Integer[] nums, TreeNode node) {

        if (node == null) {
            return node;
        }

        if ((node.index + 1) * 2 - 1 > nums.length - 1) {
            return null;
        }
        if (nums[(node.index + 1) * 2 - 1] != null) {
            // 左节点 = 父节点索引 * 2 - 1
            node.left = new TreeNode(nums[(node.index + 1) * 2 - 1], (node.index + 1) * 2 - 1);
        }
        if ((node.index + 1) * 2 > nums.length - 1) {
            return null;
        }

        if (nums[(node.index + 1) * 2] != null) {

            // 左节点 = 父节点索引 * 2
            node.right = new TreeNode(nums[(node.index + 1) * 2], (node.index + 1) * 2);
        }

        createTree(nums, node.left);
        createTree(nums, node.right);
        return node;
    }


    /**
     * 参数简化 默认以第一个为根节点
     *
     * @param nums
     * @return
     */
    public static TreeNode createTree(Integer[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return createTree(nums, new TreeNode(nums[0], 0));
    }


}

