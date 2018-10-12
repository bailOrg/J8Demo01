package com.fight.team.thread.this_escape;

import java.util.List;

/**
 * 监听线程
 * @author bail
 * @date 2018/10/9
 */
public class ListenerRunnable implements Runnable {
    private EventSource<EventListener> source;

    public ListenerRunnable(EventSource<EventListener> source) {
        this.source = source;
    }

    public void run() {
        List<EventListener> listeners = null;

        try {
            listeners = this.source.retrieveListeners();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(EventListener listener : listeners) {
            listener.onEvent(new Object());
        }
    }

}
