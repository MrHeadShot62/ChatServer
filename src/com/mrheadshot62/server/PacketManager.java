package com.mrheadshot62.server;

import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.Types;
import com.mrheadshot62.api.TypesAnswer;
import com.mrheadshot62.api.types.*;
import com.mrheadshot62.api.MultiPacket;

import com.mrheadshot62.api.types.answer.ServerAnswerAuthPacket;
import org.arcticsoft.bluebearlive.socket.ConnectionController;

/**
 * Created by DmitriyRoot on 05.01.2017.
 */

public class PacketManager{

    private static PacketManager instance = null;

    private PacketManager() {
    }

    public static synchronized PacketManager getInstance(){
        if(instance == null){
            instance = new PacketManager();
            return instance;
        }else {
            return instance;
        }
    }

    public static void PacketGenerator(ServerAnswerAuthPacket serverAnswerAuthPacket) {
        sendPackets(new MultiPacket(new Packet(serverAnswerAuthPacket, TypesAnswer.AUTHPACKET)));
    }

    private static int sendPackets(MultiPacket multiPacket) {
        return 0;
    }
}
