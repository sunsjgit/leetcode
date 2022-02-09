package com.ssj.leetcode.question6;

import java.util.ArrayList;
import java.util.List;

/**
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * 题目地址: https://leetcode-cn.com/problems/zigzag-conversion/
 */
class Solution {
    /**
     * @param str     字符串
     * @param numRows 行数
     * @return
     */
    public static String convert(String str, int numRows) {


        if (str.length() == 1 || numRows == 1) {
            return str;
        }

        // 创建行列表
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, str.length()); i++) {
            rows.add(new StringBuilder());
        }

        // 声明行位移标识 开始为从上到下
        boolean upToDown = false;
        int rowNum = 0;

        for (char c : str.toCharArray()) {
            // 在行首和行尾的时候Z字便利方向开始发生变化
            rows.get(rowNum).append(c);
            if (rowNum == 0 || rowNum == numRows - 1) {
                upToDown = !upToDown;
            }
            rowNum += upToDown ? 1 : -1;
        }

        StringBuilder sb = rows.stream().reduce((row, row2) -> row.append(row2)).get();
        return sb.toString();
    }

    /**
     * numRows = 4
     * 1 4 7
     * <p>
     * numRows - 1 的等比数列
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 3));
    }
}