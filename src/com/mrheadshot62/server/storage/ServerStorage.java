package com.mrheadshot62.server.storage;

import com.mrheadshot62.api.types.UserPacket;
import com.mrheadshot62.server.Client;
import com.mrheadshot62.server.ServerUser;
import com.sun.istack.internal.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by novak on 04.01.2017.
 */
public class ServerStorage {
    private static ServerStorage instance;


    private Storage storage;
    private ServerStorage() {
        this.storage = new Storage();
    }


    public static ServerStorage getInstance() {
        if (instance==null){
            instance = new ServerStorage();
            return instance;
        }else{
            return instance;
        }
    }

    public Set<Map.Entry<Integer,Client>> getClients(){
        return storage.getClients();
    }

    public Map<Integer, Client> getMap(){
        return storage.getMap();
    }

    public void addClient(Client client){
        storage.getMap().put(client.getId(), client);
    }

    public Client getClient(int id){
        return storage.getMap().get(id);
    }

    public void removeClient(int id){
        for (Map.Entry<Integer, Client> map:getClients()){
            if (map.getValue().getId() == id){
                getClients().remove(map);
                return;
            }
        }
    }

    public String[] getLoginAndPassUser(int id){
        SQLBuilder sql = new SQLBuilder();
        sql.addSelectQuery(Tables.USER, new String[]{Tables.USER_LOGIN, Tables.USER_PASS}, Tables.USER_ID, String.valueOf(id), 1);
        ResultSet rs = sql.executeSingle();
        String[] strings = new String[2];
        try {
            while (rs.next()){
                strings[0] = rs.getString("login");
                strings[1] = rs.getString("pass");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return strings;
    }

    public void updateSessionKey(String sessionKey, int id){
        new SQLBuilder().addUpdateQuery(Tables.USER, Tables.USER_SESSION, sessionKey, Tables.USER_ID, String.valueOf(id)).executeNoResult();
    }
}
