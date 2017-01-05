package com.mrheadshot62.server.handler.abstracts;

import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.iLogic.IPacketUserHandler;
import com.mrheadshot62.api.types.UserPacket;

/**
 * Created by novak on 05.01.2017.
 */
public abstract class AUserHandler implements IPacketUserHandler {
    @Override
    public void handlePacket(Packet p) {
        try{
            UserPacket userPacket = (UserPacket) p.getData();
            handleCommandPacket(userPacket);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected abstract void handleCommandPacket(UserPacket userPacket);
}
