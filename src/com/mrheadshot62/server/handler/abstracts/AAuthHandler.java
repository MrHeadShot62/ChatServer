package com.mrheadshot62.server.handler.abstracts;

import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.iLogic.IPacketAuthHandler;
import com.mrheadshot62.api.types.AuthPacket;

/**
 * Created by novak on 05.01.2017.
 */
public abstract class AAuthHandler implements IPacketAuthHandler {
    protected int id;
    public AAuthHandler(AuthPacket p, int id) {
        this.id = id;
        try {
            handleAuthPacket(p);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected abstract void handleAuthPacket(AuthPacket packet) throws Exception;
}
