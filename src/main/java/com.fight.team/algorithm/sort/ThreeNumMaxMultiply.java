package com.fight.team.algorithm.sort;

import java.util.Arrays;

/**
 * 给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 * 示例 1:
 * 输入: [1,2,3]
 * 输出: 6
 *
 * 示例 2:
 * 输入: [1,2,3,4]
 * 输出: 24
 *
 * 注意:
 * 给定的整型数组长度范围是[3,10的4次方]，数组中所有的元素范围是[-1000, 1000]。
 * 输入的数组中任意三个数的乘积不会超出32位有符号整数的范围。
 *
 * @author bail
 * @date 2019/4/1
 */
public class ThreeNumMaxMultiply {
    public static void main(String[] args) {
        // 最大乘积为负，负，负（max * max * max）
        System.out.println(maximumProduct(new int[]{-4, -3, -2, -1}));
        // 最大乘积为负，负，正（min * min * max）
        System.out.println(maximumProduct(new int[]{-4, -3, -2, 1}));
        // 最大乘积为负，正，正（max * max * max）
        System.out.println(maximumProduct(new int[]{-4, 1, 2}));
        // 最大乘积为正，正，正（max * max * max）
        System.out.println(maximumProduct(new int[]{-4, 0, 2, 3}));
    }

    /**
     * 求出最大乘积
     * 执行用时 : 26 ms, 在Maximum Product of Three Numbers的Java提交中击败了72.95% 的用户
     * 内存消耗 : 38.5 MB, 在Maximum Product of Three Numbers的Java提交中击败了44.90% 的用户
     *
     * 思路分析总结：
     * ① 负，负，负：假设最大乘积为3个负数相乘的结果，则这3个负数一定是数组中最大3个数（max * max * max）
     * ② 负，负，正：假设最大乘积为2个负数与1个正数相乘的结果，则这2个负数一定是数组中最小的2个数，这个正数一定是最大的数（min * min * max）
     * ③ 负，正，正：假设最大乘积为1个负数与2个正数相乘的结果，则这3个数一定是数组中最大3个数（max * max * max）
     * ④ 正，正，正：假设最大乘积为3个正数相乘的结果，则这3个数一定是数组中最大3个数（max * max * max）
     * 由此可得，只需要排序后取（max * max * max）与（min * minx * max）的最大值
     *
     * @param nums 输入数组
     * @return 最大乘积的值
     */
    private static int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        return Math.max(nums[len-1] * nums[len-2] * nums[len-3], nums[0] * nums[1] * nums[len-1]);
    }
}
