package com.mrheadshot62.server.handler;


import com.mrheadshot62.api.ServerAnswerCode;
import com.mrheadshot62.api.types.AuthPacket;

import com.mrheadshot62.api.types.answer.ServerAnswerAuthPacket;
import com.mrheadshot62.api.types.answer.ServerAnswerPacket;
import com.mrheadshot62.server.PacketManager;
import com.mrheadshot62.server.handler.abstracts.AAuthHandler;
import com.mrheadshot62.server.storage.SQLBuilder;
import com.mrheadshot62.server.storage.ServerStorage;
import com.mrheadshot62.server.storage.Tables;

import java.sql.ResultSet;
import java.sql.SQLException;

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
            String session = String.valueOf(((Math.random()*90)*50));
            ServerStorage.getInstance().updateSessionKey(session, id);
            ResultSet rs = SQLBuilder.getInstance().addSelectQuery(Tables.USER, new String[]{Tables.USER_NAME, Tables.USER_COUNTRY, Tables.USER_SESSION, Tables.USER_PERMISSIONLEVEL}, Tables.USER_ID, String.valueOf(id), 1).executeSingle();
            try {
                while (rs.next()) {
                    System.out.printf("[DEBUG] Generated new session key for #%d: %s%n", id, session);
                    PacketManager.packetGenerator(new ServerAnswerAuthPacket(rs.getString(Tables.USER_NAME), rs.getString(Tables.USER_COUNTRY), rs.getString(Tables.USER_SESSION), rs.getInt(Tables.USER_PERMISSIONLEVEL)), id);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                PacketManager.generateAnswer(ServerAnswerCode.SERVERERROR, id);
            }
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
