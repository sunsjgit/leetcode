package com.ssj.leetcode.question12;

import java.util.HashMap;

/**
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * <p>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给你一个整数，将其转为罗马数字。
 * <p>
 * <p>
 * 例如： 3494 ： MMMCDXCIV
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/integer-to-roman
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution {


    // 位数和罗马数值映射
    private static final HashMap<Integer, String[]> romeMap = new HashMap<>();

    // 最大可转换数值
    private static final int MAX_VALUE = 3999;

    static {
        romeMap.put(1, new String[]{"I", "V"});
        romeMap.put(2, new String[]{"X", "L"});
        romeMap.put(3, new String[]{"C", "D"});
        romeMap.put(4, new String[]{"M"});
    }

    public String intToRoman(int num) {

        if (num > MAX_VALUE) {
            return null;
        }

        // 结果
        StringBuilder res = new StringBuilder();
        // 位数
        int location = 0;
        // 对应位数字
        int numCur;

        while (num > 0) {
            numCur = num % 10;
            num = num / 10;
            // 位数加1 并获取对应的映射
            String[] curMap = romeMap.get(++location);
            if (numCur < 4) {
                for (int i = 0; i < numCur; i++) {
                    res.insert(0, curMap[0]);
                }
            } else if (numCur < 9) {
                // 获取差值
                int cur = numCur - 5;
                // 若小于0 则在先插入高位 再在前方插入低位
                if (cur < 0) {
                    res.insert(0, curMap[1]);
                    res.insert(0, curMap[0]);
                } else {
                    // 若大于等于0 则在先插入低位 再在前方插入高位
                    for (int i = 0; i < cur; i++) {
                        res.insert(0, curMap[0]);
                    }
                    res.insert(0, curMap[1]);
                }

            } else {
                // 若为9  则获取下一位的低位作为高位 当前位的低位作为低位
                // 先插入高位 再在前方插入低位
                String[] nextMap = romeMap.get(location + 1);
                res.insert(0, nextMap[0]);
                res.insert(0, curMap[0]);
            }

        }
        return res.toString();
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.intToRoman(3494));
    }
}