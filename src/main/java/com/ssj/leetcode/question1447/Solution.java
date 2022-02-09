package com.ssj.leetcode.question1447;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数 n ，请你返回所有 0 到 1 之间（不包括 0 和 1）满足分母小于等于  n 的 最简 分数 。分数可以以 任意 顺序返回。
 *
 * 辗转相除
 */
class Solution {


    /**
     * 递归-辗转相除
     * 19 ms	42.7 MB
     * @param a
     * @param b
     * @return
     */
    public static int isSimplifiedGcdV1(int a, int b) {

        if (a % b == 0) {
            return b;
        }
        return isSimplifiedGcdV1(b, a % b);
    }

    /**
     * 循环-辗转相除
     * 19 ms	42.6 MB
     * @param a
     * @param b
     * @return
     */
    public static int isSimplifiedGcdV2(int a, int b) {

        int temp = 0;
        while (b != 0 && a % b != 0) {
            temp = b;
            b = a % b;
            a = temp;
        }
        return b;
    }

    /**
     * 循环相减
     * 21 ms	42.8 MB
     * @param a
     * @param b
     * @return
     */
    public static int isSimplifiedGcdV3(int a, int b) {

        while (a != b) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;
    }

    public static List<String> simplifiedFractions(int n) {

        ArrayList<String> result = new ArrayList<>();
        if (n == 1) {
            return result;
        }

        // 分母从2开始 至n结束
        for (int down = 2; down <= n; down++) {
            // 分子从1开始 至down - 1结束
            for (int up = 1; up < down; up++) {

                // 需要 分母 > 分子
                if (isSimplifiedGcdV3(down, up) == 1) {
                    result.add(up + "/" + down);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {

        simplifiedFractions(5).stream().forEach(System.out::println);

    }

}