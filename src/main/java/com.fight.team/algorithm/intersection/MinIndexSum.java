package com.fight.team.algorithm.intersection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 最小索引和的交集
 * @author bail
 * @date 2019/3/30
 */
public class MinIndexSum {
    public static void main(String[] args) {
        String[] aArr = new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"};
        String[] bArr = new String[]{"KFC", "Burger King", "Tapioca Express", "Shogun"};
        System.out.println(Arrays.toString(findRestaurant(aArr, bArr)));

    }

    /**
     * 执行用时 : 37 ms, 在Minimum Index Sum of Two Lists的Java提交中击败了39.07% 的用户
     * 内存消耗 : 40 MB, 在Minimum Index Sum of Two Lists的Java提交中击败了61.90% 的用户
     * @param list1 传入数组1
     * @param list2 传入数组2
     * @return 最小索引之和的交集数组
     */
    private static String[] findRestaurant(String[] list1, String[] list2) {
        // 第一个数组的索引
        int i;
        // 第二个数组的索引
        int j;
        // 依次递增的索引和
        int sumInd = 0;
        // 结果集List
        List<String> resList = new ArrayList<>();
        // 索引和+2 <= 两个数组长度之和
        while (sumInd + 2 <= list1.length + list2.length) {
            // 在当前索引和以及数组长度范围内遍历i
            for (i = 0; i <= sumInd && i < list1.length; i++) {
                // j直接取索引和减去i的值
                j = sumInd - i;
                if (j < list2.length) {
                    // j小于自身数组大小，则进行比较，若比较结果一致则加入结果集
                    if (list1[i].equals(list2[j])) {
                        resList.add(list1[i]);
                    }
                }
            }
            if (resList.size() > 0) {
                // 得到结果值并返回
                return resList.toArray(new String[resList.size()]);
            }
            // 索引和递增
            sumInd++;
        }
        return new String[]{};
    }
}
