package com.mrheadshot62.api;

import com.mrheadshot62.api.iLogic.IPacket;

import java.io.Serializable;

/**
 * Created by DmitriyRoot on 04.01.2017.
 */
public class MultiPacket implements Serializable{
    private Packet[] packets;

    public MultiPacket(Packet... packets) {
        this.packets = packets;
    }

    public Packet[] getPackets() {
        return packets;
    }
}
