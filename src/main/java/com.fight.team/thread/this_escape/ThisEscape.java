package com.fight.team.thread.this_escape;

/**
 * 构造器中的this逸出
 * @author bail
 * @date 2018/10/9
 */
public class ThisEscape {
    public final int id;
    public final String name;

    public ThisEscape(EventSource<EventListener> source) {
        id = 1;
        source.registerListener(new EventListener() {
            public void onEvent(Object obj) {
                System.out.println("id: "+ThisEscape.this.id);
                System.out.println("name: "+ThisEscape.this.name);
            }
        });
        try {
            Thread.sleep(1000); // 调用sleep模拟其他耗时的初始化操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        name = "bail";
    }
}

