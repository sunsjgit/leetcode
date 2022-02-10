package com.ssj.leetcode.question239;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * <p>
 * 返回 滑动窗口中的最大值 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution {


    public int[] maxSlidingWindow(int[] nums, int k) {


        // 每个节点 存储值 和 对应的下标
        PriorityQueue<int[]> priorityQueue = new PriorityQueue(k, new Comparator<int[]>() {
            @Override
            public int compare(int[] node1, int[] node2) {
                // 若值不等 则以值倒序比较  若值相等 则以下标索引倒序比较
                return node1[0] != node2[0] ? node2[0] - node1[0] : node2[1] - node1[1];
            }
        });

        // 填充第一个窗口
        for (int i = 0; i < k; i++) {
            priorityQueue.offer(new int[]{nums[i], i});
        }

        int[] res = new int[nums.length - k + 1];
        // 首个窗口最大值
        res[0] = priorityQueue.peek()[0];
        // 循环滑动窗口
        for (int i = k; i < nums.length; i++) {

            priorityQueue.offer(new int[]{nums[i], i});

            // 检查最大值的下标是否在窗口内 若不在则移除
            while (priorityQueue.peek()[1] <= i - k) {
                priorityQueue.poll();
            }
            // 移除后 获取当前窗口内最大值
            res[i - k + 1] = priorityQueue.peek()[0];

        }

        return res;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ints = solution.maxSlidingWindow(new int[]{2, 3, 1, 2, 3, 5, 6}, 2);
        System.out.println(Arrays.toString(ints));
    }
}