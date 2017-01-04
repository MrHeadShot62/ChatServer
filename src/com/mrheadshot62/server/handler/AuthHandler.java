package com.mrheadshot62.server.handler;

import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.iLogic.IPacketHandler;
/**
 * Created by DmitriyRoot on 04.01.2017.
 */
public class AuthHandler implements IPacketHandler{
    @Override
    public boolean validPacket(Packet p) {
        return false;
    }
}
