package com.fight.team.thread;

import java.util.Random;

public class SubStockTask implements Runnable{
	private String name;
	private Goods goods;

	public SubStockTask(String name, Goods goods){
		this.name = name;
		this.goods = goods;
	}

	@Override
	public void run() {
		while(true){
			synchronized (goods.getLock()) {
				int num = new Random().nextInt(5)+1;
				while(goods.getStock() < num){
					try {
						System.out.println(name+" 准备买"+num+"个商品");
						System.out.println(name + " 发现只有"+goods.getStock()+"个,先干别的去了,"
								+ "等有库存了,再来购买...");
						goods.getLock().wait();
						System.out.println(name + " 听说补充过库存了,尝试购买。。。");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(name+" 成功购买了"+num+"个商品~");
				goods.setStock(goods.getStock()-num);
			}

			// 模拟隔一段时间,再次购买
			try {
				Thread.sleep(1000*new Random().nextInt(5)+1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
