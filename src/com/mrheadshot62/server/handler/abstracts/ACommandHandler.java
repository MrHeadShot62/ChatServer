package com.mrheadshot62.server.handler.abstracts;

import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.iLogic.IPacketCommandHandler;
import com.mrheadshot62.api.types.CommandPacket;

/**
 * Created by novak on 05.01.2017.
 */
public abstract class ACommandHandler implements IPacketCommandHandler{
    public ACommandHandler(CommandPacket p) {
        try {
            handleCommandPacket(p);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected abstract void handleCommandPacket(CommandPacket commandPacket) throws Exception;
}
