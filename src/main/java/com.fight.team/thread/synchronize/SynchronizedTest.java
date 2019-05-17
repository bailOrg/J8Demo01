package com.fight.team.thread.synchronize;

/**
 * Synchronized原理剖析例子
 * 编译生成class文件，然后再运行：javap -v -p -s -sysinfo -constants SynchronizedTest.class
 * @author bail
 * @date 2019/5/15
 */
public class SynchronizedTest {
    public void synMethod0() {
        synchronized (this) {
        }
    }

    public synchronized void synMethod1() {
    }

    public static synchronized void synMethod2() {
    }

}
