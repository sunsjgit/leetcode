package com.ssj.leetcode.question8;

import java.util.HashMap;
import java.util.Map;

/**
 * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
 * <p>
 * 函数 myAtoi(string s) 的算法如下：
 * <p>
 * 读入字符串并丢弃无用的前导空格
 * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
 * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
 * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
 * 如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
 * 返回整数作为最终结果。
 * 注意：
 * <p>
 * 本题中的空白字符只包括空格字符 ' ' 。
 * 除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/string-to-integer-atoi
 */
class Solution {

    /**
     * 官方答案 自动状态机
     *
     * @param s
     * @return
     */
    private static int myAtoi(String s) {

        AutoMan autoMan = new AutoMan();

        for (char c : s.toCharArray()) {
            autoMan.autoStatus(c);
        }

        return (int) autoMan.ans * (autoMan.isMinSign ? -1 : 1);
    }

    /**
     * 自动状态机
     * 列出所有可能的情况
     * <p>    ' '        +/-       number  other
     * start  start      signed    number   end
     * <p>
     * signed end        end       number   end
     * <p>
     * number end        end       number   end
     * <p>
     * end    end        end       end      end
     */
    private static class AutoMan {
        // 定义符号 是否为负数 默认为正数
        boolean isMinSign = false;
        // 定义结果 此处采用long 处理int越界情况
        long ans = 0;
        // 定义状态机状态
        String status = "start";
        // 定义状态集合
        Map<String, String[]> table = new HashMap<String, String[]>() {{
            put("start", new String[]{"start", "signed", "number", "end"});
            put("signed", new String[]{"end", "end", "number", "end"});
            put("number", new String[]{"end", "end", "number", "end"});
            put("end", new String[]{"end", "end", "end", "end"});
        }};


        public void autoStatus(char c) {
            // 获取下一状态
            this.status = table.get(status)[getStatusIndex(c)];

            // 根据当前状态处理数据 只有符号和number的时候需要处理
            // 若为符号 则更新符号状态
            if ("signed".equals(status)) {
                isMinSign = '-' == c;
            } else if ("number".equals(status)) {
                // 若为数字
                this.ans = ans * 10 + c - '0';
                // 处理越界情况 若为负数 则需要最小的小于 Integer.MIN_VALUE的绝对值 此处由于int位数不够需要转为long
                // 若为整数 需要小于Integer.MAX_VALUE
                this.ans = isMinSign ? Math.min(ans, -(long) Integer.MIN_VALUE) : Math.min(Integer.MAX_VALUE, ans);
            }
        }

        /**
         * 获取状态表列
         *
         * @param c
         * @return
         */
        public int getStatusIndex(char c) {
            if (c == ' ') {
                return 0;
            } else if (c == '+' || c == '-') {
                return 1;
            } else if (Character.isDigit(c)) {
                return 2;
            }
            return 3;
        }


    }


    /**
     * 暴力一遍一遍跑测试
     *
     * @param s
     * @return
     */
    private static int myAtoiV1(String s) {


        // 符号位 + 43 - 45
        StringBuilder sign = new StringBuilder();

        // 遍历获取数字 '0'是  '9'是 58 ' '是32
        StringBuilder strb = new StringBuilder();
        for (char c : s.toCharArray()) {
            // 去除首部空格
            if (strb.length() + sign.length() == 0 && c == 32) {
                continue;
            } else if (strb.length() == 0 && (c == 43 || c == 45)) {
                // 添加首部符号
                sign.append(c != 43 ? "-" : "+");
                continue;
            } else if (!Character.isDigit(c)) {
                // 发现中间含有不是数字的数值则不进行处理直接跳出
                break;
            }
            strb.append(c);
        }

        // 去除无效数据 头部的0
        String resultStr = strb.toString().replaceFirst("^0*", "");

        // 若长度为0 或者符号位异常 则直接返回
        if (resultStr.length() == 0 || sign.length() > 1) {
            return 0;
        }
        // 若拼装后长度大于边界长度则直接返回对应的上下界
        if (resultStr.length() > 10) {
            return "-".contentEquals(sign) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }

        // 若小于则处理并返回对应的值
        long result = Long.valueOf(sign + resultStr);


        return (int) result == result ? (int) result : "-".contentEquals(sign) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

    }


    /**
     * @param args
     */
    public static void main(String[] args) {
//        System.out.println(myAtoi("  0000000000012345678"));
//        System.out.println(myAtoi("  000-42"));
//        System.out.println(myAtoi("2147483648"));
        System.out.println(myAtoi("-91283472332"));
//        System.out.println(myAtoi("-2147483648"));
    }

}