package com.ssj.leetcode.question704;

/**
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 * <p>
 * 二分查找
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-search
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution {


    public int search(int[] nums, int target) {


        int begin = 0;
        int end = nums.length - 1;
        while (begin <= end) {
            int mid = (end + begin) >>> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                begin = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }


    public static void main(String[] args) {

        Solution solution = new Solution();
        System.out.println(solution.search(new int[]{1, 2, 5, 11, 23, 44}, 3));

    }
}