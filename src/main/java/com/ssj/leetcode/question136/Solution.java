package com.ssj.leetcode.question136;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * <p>
 * 说明：
 * <p>
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/single-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution {

    public static int singleNumber(int[] nums) {
        // 采用异或交换律
        // a ^ a = 0   a ^ 0 = a
        // a ^ b ^ a = a ^ a ^ b = b
        for (int i = 1; i < nums.length; i++) {
            nums[0] ^= nums[i];
        }
        return nums[0];
    }

    public static void main(String[] args) {

        System.out.printf("%d", singleNumber(new int[]{1, 4, 2, 3, 4, 5, 5, 6, 1, 3, 5, 1}));


    }

}