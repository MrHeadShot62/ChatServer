package com.mrheadshot62.server;

import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.TypesAnswer;
import com.mrheadshot62.api.MultiPacket;

import com.mrheadshot62.api.types.answer.ServerAnswerAuthUserPacket;
import com.mrheadshot62.api.types.answer.ServerAnswerPacket;
import com.mrheadshot62.api.types.answer.ServerAnswerUserPacket;

import com.mrheadshot62.server.serverListener.ServerListener;

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

    public synchronized static void packetGenerator(ServerAnswerAuthUserPacket serverAnswerAuthPacket, int id) {
        sendPackets(new MultiPacket(new Packet(serverAnswerAuthPacket, TypesAnswer.USERAUTHPACKET)), id);
    }
    public synchronized static void packetGenerator(ServerAnswerPacket serverAnswerPacket, int id) {
        sendPackets(new MultiPacket(new Packet(serverAnswerPacket, TypesAnswer.ONLYCODE)), id);
    }
    public synchronized static void packetGenerator(ServerAnswerUserPacket serverAnswerPacket, int id) {
        sendPackets(new MultiPacket(new Packet(serverAnswerPacket, TypesAnswer.USERPACKET)), id);
    }
    public synchronized static void generateAnswer(int code, int id){
        sendPackets(new MultiPacket(new Packet(new ServerAnswerPacket(code), TypesAnswer.ONLYCODE)), id);
    }
    private synchronized static int sendPackets(MultiPacket multiPacket, int id) {
        ServerListener.sendPacket(multiPacket, id);
        return 0;
    }
}