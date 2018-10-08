package com.fight.team.thread.visibility;

/**
 * 内存可见性demo
 *
 * @author bail
 * @date 2018/10/8
 */
public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    /**
     * 讲道理,据说有可能ReaderThread永远读不到ready为true的时候,也有可能因为重排序,输出number=0
     * @param args
     */
    public static void main(String[] args) {
        new ReaderThread().start();
        number = 2;
        ready = true;
    }
}
