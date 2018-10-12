package com.fight.team.thread.this_escape;

import java.util.ArrayList;
import java.util.List;

/**
 * 事件源
 * @author bail
 * @date 2018/10/9
 */
public class EventSource<T> {

    private final List<T> eventListeners ;

    public EventSource() {
        eventListeners = new ArrayList<>() ;
    }

    public synchronized void registerListener(T eventListener) {
        this.eventListeners.add(eventListener);
        this.notifyAll();
    }

    public synchronized List<T> retrieveListeners() throws InterruptedException {
        List<T> dest ;
        if(eventListeners.size() <= 0 ) {
            this.wait();
        }
        dest = new ArrayList<>(eventListeners.size());
        dest.addAll(eventListeners);
        return dest;
    }
}
