package com.ssj.leetcode.question142;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * <p>
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，
 * 评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。
 * 注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * <p>
 * 不允许修改 链表。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    private Set<ListNode> listNodeHashMap = new HashSet<>();

    /**
     * 哈希表
     * 递归 + Set 记录
     *
     * @param head
     * @return
     */
    public ListNode detectCycleOne(ListNode head) {

        // 若链表到末尾 或者 已经包含环路 则返回
        if (Objects.isNull(head) || listNodeHashMap.contains(head)) {
            return head;
        }

        // 记录当前节点
        listNodeHashMap.add(head);
        return detectCycleOne(head.next);
    }


    /**
     * 快慢指针
     * <p>
     * 1. 把链表分为3个部分
     * <p>
     * (1). 入环点前的长度为 D
     * (2). 入环点后相遇点前距离入环点长度为 S1
     * (3). 入环点后相遇点后距离入环点长度为 S2
     * <p>
     * 2. 首次相遇时慢指针的位置为 S = D + S1
     * 3. 快指针为： F = D + n(S1 + S2) + S1 (n 为重复跑的圈数， (S1 + S2) 为一圈的长度)
     * 4. F走的步数为S的两倍 即 D + n(S1 + S2) + S1 = 2D + 2S1
     * 5. 化简后为： D = n(S1 + S2) - S1 = (n - 1)S1 + nS2 = (n-1)(S1 + S2) + S2
     *    即： D = (n - 1)(S1 + S2) + S2
     * 6. 由于 S1 + S2 为一圈，此时我们就可以知道 n圈  + S2 就是入环点，
     * 7. 由于每次转一圈都会回到入环点，所以n * 圈 代入情景都等于回到入环点，即 n * 圈 = 0, 得出 D = S2
     * 8. 此时将其中一个指针重新指向head， 另外一个指针仍然位于相遇位置
     * 9. 根据D = S2, 将同时向后一步一步的移动，当相遇的时候即head向后移动的D个位置，即为入环点。
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {

        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (true) {
            if (fast == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }


    public static void main(String[] args) {

        // 构建链表
        ListNode head = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        head.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode2;

        Solution solution = new Solution();
        ListNode listNode = solution.detectCycle(head);
        System.out.println(listNode.val);
    }
}