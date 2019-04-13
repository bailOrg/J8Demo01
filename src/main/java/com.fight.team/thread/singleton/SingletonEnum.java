package com.fight.team.thread.singleton;

/**
 * 枚举单例模式
 *
 * @author bail
 * @date 2019/4/13
 */
public enum SingletonEnum {
    INSTANCE;

    private SingletonDemo instance;

    //JVM会保证此方法绝对只调用一次
    SingletonEnum(){
        instance = new SingletonDemo();
    }

    public SingletonDemo getInstance() {
        return instance;
    }
}
