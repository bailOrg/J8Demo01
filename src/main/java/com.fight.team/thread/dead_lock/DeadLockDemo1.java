package com.fight.team.thread.dead_lock;

/**
 * 死锁例子1
 *   小明,小红都很礼貌,必须要等对方停止鞠躬,自己才停止
 *     然后就没有然后了
 * @author bail
 * @date 2018/9/30
 */
public class DeadLockDemo1 {
    private static class Person {
        private final String name;

        private Person(String name) {
            this.name = name;
        }

        private String getName() {
            return this.name;
        }

        private synchronized void bow(Person other) {
            System.out.format("%s 向 %s 鞠躬%n", this.name, other.getName());
            other.bowBack(this);
        }

        private synchronized void bowBack(Person other) {
            System.out.format("%s 停止鞠躬%n", other.getName());
        }
    }

    public static void main(String[] args) {
        final Person xiaoMing = new Person("小明");
        final Person xiaoHong = new Person("小红");

        new Thread(()-> xiaoMing.bow(xiaoHong)).start();
        new Thread(()-> xiaoHong.bow(xiaoMing)).start();
    }
}
