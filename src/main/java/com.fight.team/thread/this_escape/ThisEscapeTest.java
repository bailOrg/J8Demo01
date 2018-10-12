package com.fight.team.thread.this_escape;

/**
 * 测试this逸出/逃逸
 * @author bail
 * @date 2018/10/9
 */
public class ThisEscapeTest {

    public static void main(String[] args) {
        EventSource<EventListener> source = new EventSource<>();
        ListenerRunnable listRun = new ListenerRunnable(source);
        Thread thread = new Thread(listRun);
        thread.start();
        ThisEscape escape1 = new ThisEscape(source);
    }
}

