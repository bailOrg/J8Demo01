package com.fight.team.algorithm.linkedlist;

/**
 * 给出一个链表，每 k 个节点一组进行翻转，并返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么将最后剩余节点保持原有顺序。
 * <p>
 * 示例 :
 * 给定这个链表：1->2->3->4->5
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * <p>
 * 说明 :
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * @author bail
 * @date 2019/4/2
 */
public class ReverseKGroup {
    /**
     * 单向链表内部类
     */
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
        @Override
        public String toString() {
            return this.val + (this.next == null ? "" : "->" + this.next.toString());
        }
    }
    /**
     * 构建一个链表
     * @return 示例链表
     */
    private static ListNode getListNode() {
        ListNode ori = new ListNode(1);
        ori.next = new ListNode(2);
        ori.next.next = new ListNode(3);
        ori.next.next.next = new ListNode(4);
        ori.next.next.next.next = new ListNode(5);
        return ori;
    }

    public static void main(String[] args) {
        ListNode ori = getListNode();
        System.out.println("原链表=====>" + ori);
        System.out.println("k=2 ======>" + reverseKGroup(ori, 2));
        ori = getListNode();
        System.out.println("k=3 ======>" + reverseKGroup(ori, 3));
    }


    /**
     * 每隔k个节点翻转一次
     * @param head 翻转前的链表
     * @param k 每个几个节点翻转
     * @return 翻转后的链表
     */
    private static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        // 同时获取3个节点,需要移动的前一个,需要移动的,需要移动的后一个, 从head开始,所以需要创建一个空节点保持队形
        ListNode dualNode = new ListNode(-1);
        dualNode.next = head;
        ListNode preNode = dualNode;
        ListNode cur = head;
        ListNode nextNode = cur.next;

        // 每k个节点翻转一次,idx用于计数
        int idx = 0;
        while(nextNode != null){
            idx ++;
            if(idx == k){
                idx = 0;
                // 找到需要翻转的k个节点
                ListNode end = nextNode;
                ListNode first = preNode.next;
                ListNode pre = preNode.next;
                cur = pre.next;
                while (cur != end){
                    nextNode = cur.next;
                    cur.next = pre;
                    pre = cur;
                    cur = nextNode;
                }
                preNode.next = pre;
                first.next = end;
                preNode = first;
            }
            nextNode = nextNode.next;
        }
        return dualNode.next;
    }
}
