package com.mrheadshot62.server.handler;


import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.types.AuthPacket;
import com.mrheadshot62.server.handler.abstracts.AAuthHandler;

class AuthHandler extends AAuthHandler{


    public AuthHandler(AuthPacket p) {
        super(p);
    }

    @Override
    protected void handleAuthPacket(AuthPacket authPacket) {
        System.out.println("PacketAuth received");
        System.out.println("Login: "+ authPacket.getLogin());
        System.out.println("Pass: "+ authPacket.getPass());
    }
}
