package com.ssj.leetcode.question70;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 * 示例 2：
 *
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/climbing-stairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution {


    public int climbStairs(int n) {
        // 0 层台阶无意义
        if (n == 0){
            return 0;
        }

        // 设置0层台阶为0
        int r = 0;
        // 1层台阶为1
        int q = 1;
        // 结果默认为1
        int res = 1;
        // 根据 fn = fn-1 + fn-2 进行计算
        for (int i = 1; i <= n; i++){
            res = r + q;
            r = q;
            q = res;
        }

        return res;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.climbStairs(3));
    }
}







