package com.fight.team;

import java.util.HashMap;

/**
 * @author bail
 * @date 2018/3/28
 */
public class HashMapDemo {
    public static void main(String[] args) {
        //调试Map
        HashMap<String,String> settings = new HashMap<>(4);
        settings.put("name","柏亮");
        settings.put("sex","男");
        System.out.println(settings.get("name"));

        settings.put("id","1001");
        settings.put("height","175cm");
        settings.put("weight","70kg");
        settings.put("age","18");


    }
}
