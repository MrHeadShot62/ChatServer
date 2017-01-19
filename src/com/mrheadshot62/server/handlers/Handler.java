package com.mrheadshot62.server.handlers;

import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.types.CorePacket;

/**
 * Created by novak on 19.01.2017.
 */
abstract class Handler {
    protected int type;

    public Handler(int type) {
        this.type = type;
    }

    protected boolean verifyPacket(Packet p) {
        System.out.println(p.getType() + " == " + type);
        return p.getType() == type;
    }

    public abstract boolean handlePacket(Packet p);

    public int getType() {
        return type;
    }
}