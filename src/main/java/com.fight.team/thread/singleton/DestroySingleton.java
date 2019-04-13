package com.fight.team.thread.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * 破坏单例例子
 *
 * @author bail
 * @date 2019/4/13
 */
public class DestroySingleton {
    public static void main(String[] args) throws Exception {
        DestroyDoubleCheckSingleton();
        DestroyEnumSingleton();
    }

    /**
     * 破坏double check单例模式
     */
    private static void DestroyDoubleCheckSingleton() throws Exception {
        DoubleCheck oldObj = DoubleCheck.getInstance();

        Field field = DoubleCheck.class.getDeclaredField("instance");
        field.setAccessible(true);

        Constructor<DoubleCheck> constructor = DoubleCheck.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        // 将instance变量指向一个新对象,破坏单例
        field.set(DoubleCheck.class, constructor.newInstance());
        // 与上一行同理,因为instance是一个类变量,通过类修改和通过实例修改,效果相同
//        field.set(oldObj, constructor.newInstance());

        DoubleCheck newObj = DoubleCheck.getInstance();
        System.out.println(oldObj == newObj);
    }

    /**
     * 破坏enum单例模式
     */
    private static void DestroyEnumSingleton() throws Exception {
        SingletonDemo oldObj = SingletonEnum.INSTANCE.getInstance();
        Field field = SingletonEnum.class.getDeclaredField("instance");
        field.setAccessible(true);


        Constructor<SingletonEnum> constructor = SingletonEnum.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        field.set(constructor.newInstance(), new SingletonDemo());
        SingletonDemo newObj = SingletonEnum.INSTANCE.getInstance();
        System.out.println(oldObj == newObj);
    }
}
