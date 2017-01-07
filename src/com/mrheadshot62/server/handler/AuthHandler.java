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
        System.out.println("PacketAuth received");
        System.out.println("Login: "+ authPacket.getLogin());
        System.out.println("Pass: "+ authPacket.getPass());
        System.out.println("[DEBUG] id:" +id);

        if (checkAuth(authPacket)){
            String session = String.valueOf((Math.random()*9)*5);
            ServerStorage.getInstance().updateSessionKey(session, id);
            ResultSet rs = new SQLBuilder().addSelectQuery(Tables.USER, new String[]{Tables.USER_NAME, Tables.USER_COUNTRY, Tables.USER_SESSION, Tables.USER_PERMISSIONLEVEL}, Tables.USER_ID, String.valueOf(id), 1).executeSingle();
            try {
                while (rs.next()) {
                    PacketManager.packetGenerator(new ServerAnswerAuthPacket(rs.getString(Tables.USER_NAME), rs.getString(Tables.USER_COUNTRY), rs.getString(Tables.USER_SESSION), rs.getInt(Tables.USER_PERMISSIONLEVEL)), id);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            PacketManager.packetGenerator(new ServerAnswerPacket(ServerAnswerCode.UNAUTHRIZED), id);
            System.out.println("UnAuthorized #"+id);
        }
    }

    private boolean checkAuth(AuthPacket authPacket){
        String[] strings = ServerStorage.getInstance().getLoginAndPassUser(id);
        if (authPacket.getLogin().equals(strings[0]) && authPacket.getPass().equals(strings[1])){
            return true;
        }else{
            return false;
        }
    }
}
