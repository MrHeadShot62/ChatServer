package com.mrheadshot62.server.handler;


import com.mrheadshot62.api.ServerAnswerCode;
import com.mrheadshot62.api.types.AuthPacket;

import com.mrheadshot62.api.types.User;
import com.mrheadshot62.api.types.answer.ServerAnswerAuthUserPacket;
import com.mrheadshot62.server.PacketManager;
import com.mrheadshot62.server.handler.abstracts.AAuthHandler;
import com.mrheadshot62.server.storage.ServerStorage;

import java.util.UUID;


class AuthHandler extends AAuthHandler{


    public AuthHandler(AuthPacket p, int id) {
        super(p, id);
    }

    @Override
    protected void handleAuthPacket(AuthPacket authPacket) {
        System.out.printf("[DEBUG]PacketAuth received: %n");
        System.out.printf("[DEBUG]              Login: %s%n",authPacket.getLogin());
        System.out.printf("[DEBUG]               Pass: %s%n",authPacket.getPass());
        if (checkAuth(authPacket)){
            String session = UUID.randomUUID().toString();
            ServerStorage.getInstance().updateSessionKey(session, id);
            System.out.printf("[DEBUG] Generated new session key for #%d: %s%n", id, session);
            PacketManager.packetGenerator(new ServerAnswerAuthUserPacket(session, ServerStorage.getInstance().getUser(id)), id);
        }else{
            PacketManager.generateAnswer(ServerAnswerCode.FORBIDDEN, id);
        }

    }

    private boolean checkAuth(AuthPacket authPacket){
        String[] strings = ServerStorage.getInstance().getLoginAndPassUser(id);
        System.out.printf("[DEBUG] %s == %s%n", authPacket.getLogin(), strings[0]);
        System.out.printf("[DEBUG] %s == %s%n", authPacket.getPass(), strings[1]);
        if (authPacket.getLogin().equals(strings[0]) && authPacket.getPass().equals(strings[1])){
            System.out.printf("[DEBUG] Access granted for #%d%n", id);
            return true;
        }else{
            System.out.printf("[DEBUG] Access denied for #%d%n", id);
            return false;
        }
    }
}
