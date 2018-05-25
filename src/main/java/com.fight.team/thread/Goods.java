package com.fight.team.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Goods {
	private int stock = 0;
	private Object lock = new Object();

	public static void main(String[] args) throws Exception{
		List<Goods> list = new ArrayList<Goods>();
		list.iterator().hasNext();
		final Goods goods = new Goods();
		for(int i=0; i<(new Random().nextInt(5));i++){
			new Thread(new SubStockTask("柏亮",goods)).start();
			new Thread(new SubStockTask("条条",goods)).start();
			new Thread(new SubStockTask("年糕",goods)).start();
		}

		for(int i=0; i<(new Random().nextInt(5));i++){
			new Thread(new AddStockTask("管理员",goods)).start();
			Thread.sleep(1000*new Random().nextInt(10)+1);
		}
	}

	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public Object getLock() {
		return lock;
	}
	public void setLock(Object lock) {
		this.lock = lock;
	}


}
