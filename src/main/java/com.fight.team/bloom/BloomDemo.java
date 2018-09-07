package com.fight.team.bloom;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.*;

/**
 *
 *
 * @author bail
 * @date 2018/6/6
 */
public class BloomDemo {
	private static int size = 10000000;
	private static BloomFilter<String> bloomFilter = BloomFilter
			.create(Funnels.stringFunnel(Charsets.UTF_8), size);

	public static void main(String[] args) {
		bloomFunc();
	}

	private static void bloomFunc(){
		// 1.创建布隆过滤器,塞入100w个值
		String strTmp;
		for (int i = 0; i < size; i++) {
			strTmp = UUID.randomUUID().toString();
			bloomFilter.put(strTmp);
		}

		// 2.判断这一百万个数中是否包含29999这个数
		long startTime = System.nanoTime();
		if (bloomFilter.mightContain("29999")) {
			System.out.println("bingo!");
		}
		long endTime = System.nanoTime();
		// 3.计算过滤需要的时间

		System.out.println("bloom过滤需要的时间为： " + (endTime - startTime) + "纳秒");

		// 4.计算误判率
		int errorCount = 0;
		// 故意取1w个不在过滤器里的值，看看有多少个会被认为在过滤器里
		for (int i = 0; i < 10000; i++) {
			if (bloomFilter.mightContain(i+"")) {
				errorCount++;
			}
		}
		// 默认误判率是0.03,误判率越低 -> bitmap长度越大 , 反之也成立
		System.out.println("误判率：" + errorCount / 10000f);

		System.out.println(bloomFilter.approximateElementCount());
	}
}
