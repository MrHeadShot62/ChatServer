package com.mrheadshot62.server.storage;

import com.mrheadshot62.server.Client;
import com.mrheadshot62.server.User;
import com.sun.istack.internal.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;
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


    public synchronized static ServerStorage getInstance() {
        if (instance==null){
            instance = new ServerStorage();
            return instance;
        }else{
            return instance;
        }
    }

    public synchronized Set<Map.Entry<Integer,Client>> getClients(){
        return storage.getClients();
    }

    public synchronized Map<Integer, Client> getMap(){
        return storage.getMap();
    }

    public synchronized void addClient(Client client){
        storage.getMap().put(client.getId(), client);
    }

    public synchronized Client getClient(int id){
        return storage.getMap().get(id);
    }

    public synchronized void removeClient(int id){
        for (Map.Entry<Integer, Client> map:getClients()){
            if (map.getValue().getId() == id){
                getClients().remove(map);
                return;
            }
        }
    }

    /**
     * @param id пользователя
     * @return String[0] - логин<br>
     *         String[1] - пароль
     */
    public synchronized String[] getLoginAndPassUser(int id){
        SQLBuilder sql = SQLBuilder.getInstance();
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

    public synchronized void updateSessionKey(String sessionKey, int id){
        SQLBuilder.getInstance().addUpdateQuery(Tables.USER, Tables.USER_SESSION, sessionKey, Tables.USER_ID, String.valueOf(id)).executeNoResult();
    }

    public synchronized void addUser(User user){
        if (!storage.getUsers().containsKey(user.getId())){
            storage.getUsers().put(user.getId(), user);
        }
    }

    public synchronized boolean hasUser(int id){
        if (storage.getUsers().containsKey(id)){
            return true;
        }else {
            return false;
        }
    }

    @Nullable
    public synchronized User getUser(int id){
        if (storage.getUsers().containsKey(id)){
            return storage.getUsers().get(id);
        }else{
            return null;
        }
    }
}
