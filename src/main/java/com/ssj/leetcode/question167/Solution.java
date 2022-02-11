package com.ssj.leetcode.question167;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 给你一个下标从 1 开始的整数数组 numbers ，该数组已按 非递减顺序排列  ，请你从数组中找出满足相加之和等于目标数 target 的两个数。
 * 如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。
 * <p>
 * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
 * <p>
 * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
 * <p>
 * 你所设计的解决方案必须只使用常量级的额外空间。
 * <p>
 * 空间复杂度要求O(1)。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution {


    /**
     * 双指针
     * <p>
     * 由于是有序的 可以依赖此性质 使用头尾指针处理
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {

        for (int left = 0, right = numbers.length - 1; left < right; ) {

            // 若相加之和大于target 则尾指针左移
            if (numbers[left] + numbers[right] > target) {
                right--;
            } else if (numbers[left] + numbers[right] < target) {
                // 若小于target 则头指针右移
                left++;
            } else {
                return new int[]{left + 1, right + 1};
            }
        }

        return null;
    }


    /**
     * 动态规划
     * <p>
     * 你所设计的解决方案必须只使用常量级的额外空间 (不符合)
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSumFail(int[] numbers, int target) {

        HashMap<Integer, Integer> result = new HashMap<>();

        int[] ans = new int[2];
        for (int i = 0; i < numbers.length; i++) {
            if (result.containsKey(numbers[i])) {
                ans[0] = result.get(numbers[i]);
                ans[1] = i + 1;
                break;
            }
            result.put(target - numbers[i], i + 1);
        }
        return ans;
    }


    public static void main(String[] args) {

        Solution solution = new Solution();
        int[] ints = solution.twoSum(new int[]{2, 3, 4, 5}, 5);
        System.out.println(Arrays.toString(ints));
    }
}