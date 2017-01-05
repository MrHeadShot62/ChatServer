package com.mrheadshot62.server.clientListener;

import com.mrheadshot62.api.MultiPacket;
import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.Types;
import com.mrheadshot62.api.streams.BlueBearInputStream;
import com.mrheadshot62.api.types.*;
import com.mrheadshot62.server.Client;
import com.mrheadshot62.server.handler.MainHandler;


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
            }
        }
    }



    protected void onReceiveMultiPacket(MultiPacket p){
        new MainHandler(p.getPackets()){
            @Override
            public void onReceivedAuthPacket(AuthPacket p) {
                super.onReceivedAuthPacket(p);
                onReceiveAuthPacket(p);
            }

            @Override
            public void onReceivedCommandPacket(CommandPacket p) {
                super.onReceivedCommandPacket(p);
                onReceiveCommandPacket(p);
            }

            @Override
            public void onReceivedImagePacket(ImagePacket p) {
                super.onReceivedImagePacket(p);
                onReceiveImagePacket(p);
            }

            @Override
            public void onReceivedUserPacket(UserPacket p) {
                super.onReceivedUserPacket(p);
                onReceiveUserPacket(p);
            }
        };
    }


    protected abstract void onReceiveImagePacket(ImagePacket image);
    protected abstract void onReceiveAuthPacket(AuthPacket auth);
    protected abstract void onReceiveCommandPacket(CommandPacket command);
    protected abstract void onReceiveUserPacket(UserPacket user);
    protected abstract void onClientDisconnected(Client client);
}
