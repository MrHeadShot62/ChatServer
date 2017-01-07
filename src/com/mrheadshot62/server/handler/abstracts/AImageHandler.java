package com.mrheadshot62.server.handler.abstracts;

import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.iLogic.IPacketImageHandler;
import com.mrheadshot62.api.types.ImagePacket;

/**
 * Created by novak on 05.01.2017.
 */
public abstract class AImageHandler implements IPacketImageHandler{
    protected int id;
    public AImageHandler(ImagePacket p, int id) {
        this.id = id;
        try {
            handleImagePacket(p);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    protected abstract void handleImagePacket(ImagePacket imagePacket) throws Exception;
}
