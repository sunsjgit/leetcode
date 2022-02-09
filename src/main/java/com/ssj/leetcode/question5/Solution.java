package com.ssj.leetcode.question5;


/**
 *
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *
 * 题目地址: https://leetcode-cn.com/problems/longest-palindromic-substring/
 */
class Solution {
    private static String longestPalindrome(String s) {


        // 声明窗口坐标
        int[] range = new int[2];
        for (int i = 0; i < s.length(); i++) {
            // 获取以当前坐标为中心的回文的窗口坐标
            getRangeMax(i, s, range);
        }
        return s.substring(range[0], range[1] + 1);
    }


    private static void getRangeMax(int i, String s, int[] range) {

        int left = i;
        int right = i;
        // 计算右边界
        while (right < s.length() - 1 && s.charAt(left) == s.charAt(right + 1)) {
            right++;
        }

        // 开始向两边进行移动比较
        while (left - 1 >= 0 && right + 1 < s.length() && s.charAt(left - 1) == s.charAt(right + 1)) {
            left--;
            right++;
        }

        if (right - left > range[1] - range[0]) {
            range[0] = left;
            range[1] = right;
        }

    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("asdsadas"));
    }
}