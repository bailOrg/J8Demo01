package com.fight.team.bloom;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.ArrayList;
import java.util.List;

/**
 * 布隆过滤器demo
 *
 * @author bail
 * @date 2018/6/6
 */
public class BloomDemo {
	private static int size = 1000000;

	private static BloomFilter<Integer> bloomFilter = BloomFilter
			.create(Funnels.integerFunnel(), size);

	public static void main(String[] args) {
		// 创建布隆过滤器,塞入100w个值
		for (int i = 0; i < size; i++) {
			bloomFilter.put(i);
		}
		long startTime = System.nanoTime();
		// 判断这一百万个数中是否包含29999这个数
		if (bloomFilter.mightContain(29999)) {
			System.out.println("bingo!");
		}
		long endTime = System.nanoTime();
		// 计算时间
		System.out.println("过滤需要的时间为： " + (endTime - startTime)/1000000 + "毫秒");

		List<Integer> list = new ArrayList<>(10000);
		// 故意取1w个不在过滤器里的值，看看有多少个会被认为在过滤器里
		for (int i = size + 10000; i < size + 20000; i++) {
			if (bloomFilter.mightContain(i)) {
				list.add(i);
			}
		}
		// 默认误判率是0.03,误判率越低 -> bitmap长度越大 , 反之也成立
		System.out.println("误判率：" + list.size() / 10000f);

	}
}
