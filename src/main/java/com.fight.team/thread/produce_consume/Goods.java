package com.fight.team.thread.produce_consume;

public class Goods {
	/**
	 * 商品库存
	 */
	private int stock = 0;
	/**
	 * 库存锁
	 */
	private Object lock = new Object();

	public static void main(String[] args) {
		final Goods goods = new Goods();
		// 模拟购买商品的用户
		new Thread(new SubStockTask("柏亮",goods)).start();

		// 模拟补充库存的管理员
		new Thread(new AddStockTask("管理员",goods)).start();
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
}
