package com.mrheadshot62.server.handler.abstracts;

import com.mrheadshot62.api.iLogic.IPacketUserHandler;
import com.mrheadshot62.api.types.UserPacket;

/**
 * Created by novak on 05.01.2017.
 */
public abstract class AUserHandler implements IPacketUserHandler {
    protected int id;
    public AUserHandler(UserPacket p, int id) {
        this.id = id;
        try {
            handleCommandPacket(p);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected abstract void handleCommandPacket(UserPacket userPacket);
}
