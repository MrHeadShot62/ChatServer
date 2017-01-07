package com.mrheadshot62.server;

import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.TypesAnswer;
import com.mrheadshot62.api.MultiPacket;

import com.mrheadshot62.api.types.answer.ServerAnswerAuthPacket;

/**
 * Created by DmitriyRoot on 05.01.2017.
 */

public class PacketManager{

//    private static PacketManager instance = null;

    private PacketManager() {
    }

//    public static PacketManager getInstance(){
//        if(instance == null){
//            instance = new PacketManager();
//            return instance;
//        }else {
//            return instance;
//        }
//    }

    public static void packetGenerator(ServerAnswerAuthPacket serverAnswerAuthPacket, int id) {
        sendPackets(new MultiPacket(new Packet(serverAnswerAuthPacket, TypesAnswer.AUTHPACKET)), id);
    }

    private static int sendPackets(MultiPacket multiPacket, int id) {
        return 0;
    }
}
