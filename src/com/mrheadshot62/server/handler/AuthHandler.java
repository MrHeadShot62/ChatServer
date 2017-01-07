package com.mrheadshot62.server.handler;


import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.types.AuthPacket;
import com.mrheadshot62.server.handler.abstracts.AAuthHandler;
import com.mrheadshot62.server.storage.SQLBuilder;
import com.mrheadshot62.server.storage.ServerStorage;

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

        SQLBuilder sql = new SQLBuilder();
        sql.addSelectQuery(ServerStorage.USER_TABLE, new String[]{"login, pass"}, "id", String.valueOf(id), 1);
        ResultSet rs = sql.executeSingle();
        String login=null, pass=null;
        try {
            while (rs.next()){
                login = rs.getString("login");
                pass = rs.getString("pass");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (authPacket.getLogin().equals(login) && authPacket.getPass().equals(pass)){
            String session = String.valueOf((Math.random()*9)*5);
            new SQLBuilder().addUpdateQuery(ServerStorage.USER_TABLE, "session", session, "id", String.valueOf(id)).executeNoResult();

        }else{

        }
    }

    private boolean checkAuth(){

    }



}
