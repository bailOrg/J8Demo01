package com.fight.team.thread;

import java.util.Random;

public class AddStockTask implements Runnable{
	private String name;
	private Goods goods;

	public AddStockTask(){}

	public AddStockTask(String name, Goods goods){
		this.name = name;
		this.goods = goods;
	}

	@Override
	public void run() {
		while(true){
			synchronized (goods.getLock()) {
				int num = new Random().nextInt(10)+1;
				goods.setStock(goods.getStock()+num);
				System.out.println(name+" 增加了"+num+"个库存");
				goods.getLock().notifyAll();
				System.out.println(" 暗箱操作中。。。\n");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(1000*new Random().nextInt(10)+1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}



}