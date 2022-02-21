package com.ssj.leetcode.question37;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 编写一个程序，通过填充空格来解决数独问题。
 * <p>
 * 数独的解法需 遵循如下规则：
 * <p>
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 * <p>
 * <p>
 * 提示：
 * <p>
 * board.length == 9
 * board[i].length == 9
 * board[i][j] 是一位数字或者 '.'
 * 题目数据 保证 输入数独仅有一个解
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sudoku-solver
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution {

    private int[] line = new int[9];
    private int[] column = new int[9];
    private int[][] block = new int[3][3];
    private boolean valid = false;
    private List<int[]> spaces = new ArrayList<>();

    public void solveSudoku(char[][] board) {
        // 一行一行的遍历
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                // 发现为空的 即添加对应坐标
                if (board[i][j] == '.') {
                    spaces.add(new int[]{i, j});
                } else {
                    // 若不为空的 则加入位图
                    int digit = board[i][j] - '0' - 1;
                    flip(i, j, digit);
                }
            }
        }

        dfs(board, 0);
    }

    /**
     * 一个一个的处理spaces里面的空格
     * 深度优先搜索
     *
     * @param board
     * @param pos
     */
    public void dfs(char[][] board, int pos) {
        // 若已经处理完 则valid标记空已经处理完 并返回
        if (pos == spaces.size()) {
            valid = true;
            return;
        }

        int[] space = spaces.get(pos);
        int i = space[0], j = space[1];
        // 0x1ff：111111111 9个1, 相当于 (1 << 9) - 1, 与之做位运算可以只考虑低9位的信息
        // | 或运算 检查 当前横纵坐标和3*3矩阵中 都已经有了那些数了， 然后取反即为可以填入的数字位图
        // 由于int为32位 取反后高位会变为1 ，所以需要和 0x1ff进行与运算  取低位部分的位图
        int mask = ~(line[i] | column[j] | block[i / 3][j / 3]) & 0x1ff;
        // 位图中若有数字可以填写 且空白未处理完 则进行处理
        // mask &= (mask - 1) : 在不改变1索引位置的情况下，一个一个的试可以填入的数字
        // 例如：1011 & 1010 = 1010， 1010 & 1001 = 1000， 1000 & 0111 = 0000
        for (; mask != 0 && !valid; mask &= (mask - 1)) {
            // java中负数是采用补位方式实现的 -mask 相当于 ~mask + 1
            // 例如 10010 的 -(10010) 为 ~10010 + 1 = 01110 ， 10010 & 01110 = 00010 可以得到最低位的1
            int digitMask = mask & (-mask);
            // 找出最低位的1所在的位置 即为对应的数字
            int digit = Integer.bitCount(digitMask - 1);
            // 将数字放入对应的行、列、矩阵的位图中
            flip(i, j, digit);
            // 填入数字 填写下一个空格时 此数字为已填写
            board[i][j] = (char) (digit + '0' + 1);
            // 填写下一个空格 不停递归 查看当前数字是否满足将全部空格填满的条件 即 valid = true
            dfs(board, pos + 1);
            // 将当前数字 覆盖之前填入的数字
            flip(i, j, digit);
        }
    }

    /**
     * @param i     横坐标
     * @param j     纵坐标
     * @param digit 目标数字的索引
     */
    public void flip(int i, int j, int digit) {
        // 行 每个元素都以9个位标识
        line[i] ^= (1 << digit);
        // 列
        column[j] ^= (1 << digit);
        // 实线分割的九宫格
        block[i / 3][j / 3] ^= (1 << digit);
    }

    public static void main(String[] args) {

        // 组装数独 空格用 . 表示
        char[][] sudoku = new char[][]{{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, {
                '6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'}, {
                '8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'}, {
                '7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'}, {
                '.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        Solution solution = new Solution();
        solution.solveSudoku(sudoku);
        System.out.println(Arrays.deepToString(sudoku));

//        System.out.println(Integer.toBinaryString(0x1ff));
//        System.out.println(Integer.toBinaryString(2));
//        System.out.println(Integer.toBinaryString(-2));
//        System.out.println(Integer.toBinaryString(2 & -2));
//        System.out.println(Integer.toBinaryString(2 & (~2 + 1)));
//        System.out.println(Integer.toBinaryString(~((1 << 9) - 1) & 0x1ff));
//        System.out.println(Integer.toBinaryString(11));
//        System.out.println(Integer.toBinaryString(11 - 1));
//        System.out.println(Integer.toBinaryString(11 & 10));
//        System.out.println(Integer.toBinaryString(10));
//        System.out.println(Integer.toBinaryString(10 - 1));
//        System.out.println(Integer.toBinaryString(10 & 9));

    }
}