package com.ssj.leetcode.question283;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 * <p>
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/move-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution {


    public void moveZeroes(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                // 碰到为0的 则后面元素移动长度+1
                count++;
            } else if (count > 0) {
                // 碰到一个不为0的 则与第一个0交换位置
                nums[i] ^= nums[i - count];
                nums[i - count] ^= nums[i];
                nums[i] ^= nums[i - count];
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.moveZeroes(new int[]{0, 1, 0, 3, 12});

    }
}