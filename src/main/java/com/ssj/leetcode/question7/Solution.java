package com.ssj.leetcode.question7;

/**
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * <p>
 * 如果反转后整数超过 32 位的有符号整数的范围 [−2^31,  2^31 − 1] ，就返回 0。
 * <p>
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 */
class Solution {


    private static int reverse(int i) {
        long revese = 0;
        while (i != 0) {
            if ((int) revese != revese) {
                return 0;
            }
            revese = revese * 10 + i % 10;
            i = i / 10;
        }
        return (int) revese;
    }


    /**
     * @param x 整数x
     * @return
     */
    public static int reverseV1(int x) {

        // 转换类型
        StringBuilder input = new StringBuilder(String.valueOf(x));

        // 定义上下界(无符号)
        String min = "2147483648";
        String max = "2147483647";
        // 处理若用户输入小于0 则先去除符号
        boolean minusSign = x < 0;
        if (minusSign) {
            input.replace(0, 1, "");
        }

        // 比较长度 若长度大于 则直接返回0
        String reverse = input.reverse().toString();
        if (input.length() > max.length()) {
            return 0;
        } else if (input.length() < max.length()) {
            // 若小于则 直接返回倒置
            Integer reverseInt = Integer.valueOf(reverse);
            return minusSign ? -1 * reverseInt : reverseInt;
        } else {
            // 若长度相等则比较后返回
            boolean gtMaxFlag = !minusSign && reverse.compareTo(max) > 0;
            boolean ltMinFlag = minusSign && reverse.compareTo(min) > 0;
            if (gtMaxFlag || ltMinFlag) {
                return 0;
            }
            Integer reverseInt = Integer.valueOf(reverse);
            return minusSign ? -1 * reverseInt : reverseInt;
        }

    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(reverse(11112));
    }

}