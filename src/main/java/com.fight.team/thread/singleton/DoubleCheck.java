package com.fight.team.thread.singleton;

/**
 * double check 单例
 * 反射,序列化可破坏单例
 *
 * @author bail
 * @date 2019/4/13
 */
public class DoubleCheck {
    private static volatile DoubleCheck instance = null;

    private DoubleCheck() {
    }

    public static DoubleCheck getInstance() {
        if (instance == null) {
            synchronized (DoubleCheck.class) {
                if (instance == null) {
                    instance = new DoubleCheck();
                }
            }
        }
        return instance;
    }
}
