package com.mrheadshot62.server.handler.abstracts;

import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.iLogic.IPacketCommandHandler;
import com.mrheadshot62.api.types.CommandPacket;

/**
 * Created by novak on 05.01.2017.
 */
public abstract class ACommandHandler implements IPacketCommandHandler{
    protected int id;
    public ACommandHandler(CommandPacket p, int id) {
        this.id = id;
        try {
            handleCommandPacket(p);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected abstract void handleCommandPacket(CommandPacket commandPacket) throws Exception;
}
