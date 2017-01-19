package com.mrheadshot62.server.clientListener;

import com.mrheadshot62.api.MultiPacket;
import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.streams.BlueBearInputStream;
import com.mrheadshot62.api.types.*;
import com.mrheadshot62.server.Client;
import com.mrheadshot62.server.handlers.MainHandler;


abstract class AbstractClientListener extends Thread{
    protected Client client;
    private BlueBearInputStream input;

    AbstractClientListener(Client client) {
        this.client = client;
        this.input = client.getInput();
    }

    @Override
    public synchronized void start() {
        super.start();
        System.out.println("Started client listener #"+client.getId());
    }

    @Override
    public void run() {
        while (input != null){
            try {
                MultiPacket multiPacket = input.readMultiPacket();
                onReceiveMultiPacket(multiPacket);
            } catch (Exception e) {
                e.printStackTrace();
                onClientDisconnected(client);
                return;
            }
        }
        onClientDisconnected(client);
    }



    protected void onReceiveMultiPacket(MultiPacket p){
        System.out.println("receivemultipacket "+p.getId());
        MainHandler mainHandler = new MainHandler(client.getId());
        for (Packet packet:p.getPackets()){
            System.out.println(packet.getType());
            mainHandler.handle(packet);
        }
    }


    protected abstract void onReceiveImagePacket(ImagePacket image);
    protected abstract void onReceiveAuthPacket(AuthorisationPacket auth);
    protected abstract void onReceiveCommandPacket(CommandPacket command);
    protected abstract void onReceiveUserPacket(UserPacket user);
    protected abstract void onClientDisconnected(Client client);
}
