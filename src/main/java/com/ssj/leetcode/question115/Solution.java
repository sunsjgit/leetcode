package com.ssj.leetcode.question115;

import java.util.Arrays;

/**
 * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
 * <p>
 * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
 * <p>
 * 题目数据保证答案符合 32 位带符号整数范围。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/distinct-subsequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */


class Solution {


    public int numDistinct(String s, String t) {

        int m = s.length();
        int n = t.length();

        // 若t.length > s.length 则返回 0
        if (n > m) {
            return 0;
        }

        int[][] dp = new int[m + 1][n + 1];

        // 假设第一个字符为空串  空串可以为任何字符串的字串 且数量为1
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }

        // 循环分治处理各个子串
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
//            printDp(dp);
        }
        return dp[m][n];
    }

    private void printDp(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        System.out.println("---------------");
        System.out.println("---------------");
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numDistinct("123123", "123"));
    }
}