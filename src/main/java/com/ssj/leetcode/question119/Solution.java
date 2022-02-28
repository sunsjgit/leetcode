package com.ssj.leetcode.question119;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
 * <p>
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 * <p>
 * 示例 1:
 * <p>
 * 输入: rowIndex = 3
 * 输出: [1,3,3,1]
 * 示例 2:
 * <p>
 * 输入: rowIndex = 0
 * 输出: [1]
 * 示例 3:
 * <p>
 * 输入: rowIndex = 1
 * 输出: [1,1]
 * <p>
 * 进阶：
 * <p>
 * 你可以优化你的算法到 O(rowIndex) 空间复杂度吗？
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pascals-triangle-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution {


    /**
     * 滚动数据 + 动态规划
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {

        // 声明对应行数据数量的数组
        Integer[] dp = new Integer[rowIndex + 1];
        // 填充1
        Arrays.fill(dp, 1);
        // 从第三行开始
        for (int i = 2; i < dp.length; i++) {
            // 倒序滚动数组 且 当前行首尾不处理默认为1
            for (int j = i - 1; j > 0; j--){
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        List<Integer> res = Arrays.asList(dp);
        return res;
    }


    /**
     * 滚动数组
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRowMy(int rowIndex) {


        if (rowIndex == 0) {
            return Arrays.asList(1);
        }

        if (rowIndex == 1) {
            return Arrays.asList(1, 1);
        }

        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.offer(1);
        queue.offer(1);
        // 从第三行开始
        for (int i = 2; i <= rowIndex; i++) {
            // 从第二个开始计算
            // 当前位置 = 左上方和右上方的数的和
            for (int j = 1; j < i; j++) {
                queue.offer(queue.poll() + queue.peek());
            }
            // 去掉右上方的最后一个数
            queue.poll();
            // 添加首尾
            queue.addFirst(1);
            queue.addLast(1);
        }
        return queue;

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.getRow(3));
    }
}