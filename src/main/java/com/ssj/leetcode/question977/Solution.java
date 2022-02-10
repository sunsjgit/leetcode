package com.ssj.leetcode.question977;

import java.util.Arrays;

/**
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 解释：平方后，数组变为 [16,1,0,9,100]
 * 排序后，数组变为 [0,1,9,16,100]
 * 示例 2：
 * <p>
 * 输入：nums = [-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/squares-of-a-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution {


    public int[] sortedSquares(int[] nums) {
        int[] res = new int[nums.length];

        // 若为一个数字 则直接返回
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                count++;
                // 此处先默认负数为最大
                res[nums.length - i - 1] = nums[i] * nums[i];
            } else if (count > 0) {
                // 获取负数最大的数字
                int negativeAbs = Math.abs(nums[count - 1]);
                // 若小于当前非负数最小 则结果第一位为负数最大的数字 位置为 i - count
                if (negativeAbs < nums[i]) {
                    res[i - count] = negativeAbs * negativeAbs;
                    count--;
                    i--;
                } else {
                    res[i - count] = nums[i] * nums[i];
                }
            } else {
                res[i] = nums[i] * nums[i];
            }
        }
        return res;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ints = solution.sortedSquares(new int[]{-1});
        System.out.println(Arrays.toString(ints));
    }
}