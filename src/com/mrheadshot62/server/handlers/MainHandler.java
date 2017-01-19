package com.mrheadshot62.server.handlers;

import com.mrheadshot62.api.Packet;

import java.util.HashMap;

/**
 * Created by novak on 19.01.2017.
 */
public class MainHandler {
    private static HashMap<Integer, Handler> handlers=new HashMap<>();
    public MainHandler(String id){

    }

    public static void addHandler(Handler handler){
        if (!handlers.containsKey(handler.getType())) {
            handlers.put(handler.getType(), handler);
        }
    }

    public void handle(Packet p){
        if (handlers.containsKey(p.getType())) {
            handlers.get(p.getType()).handlePacket(p);

        }
    }
}
