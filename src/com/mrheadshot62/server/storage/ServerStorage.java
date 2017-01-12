package com.mrheadshot62.server.storage;

import com.mrheadshot62.server.Client;
import com.mrheadshot62.server.User;
import com.sun.istack.internal.Nullable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by novak on 04.01.2017.
 */
public class ServerStorage {
    private static ServerStorage instance;
    private static HashMap<String, String> preparedStatementHashMap = new HashMap<>();
    private Storage storage;
    private ServerStorage() {
        this.storage = new Storage();
    }

    static{
        preparedStatementHashMap.put("updateSessionKey",String.format("UPDATE %s SET `%s` = ? WHERE `id` = ?", Tables.USER, Tables.USER_SESSION));
        preparedStatementHashMap.put("getLoginAndPass",String.format("SELECT `%s`, `%s` FROM %s WHERE `id` = ?", Tables.USER_LOGIN, Tables.USER_PASS, Tables.USER));
        preparedStatementHashMap.put("getAuthUser",String.format("SELECT `%s`, `%s`, `%s`, `%s` FROM %s WHERE `id` = ?", Tables.USER_NAME, Tables.USER_COUNTRY, Tables.USER_SESSION, Tables.USER_PERMISSIONLEVEL, Tables.USER));
        preparedStatementHashMap.put("hasImageName",String.format("SELECT 0 FROM %s WHERE %s = ?", Tables.IMAGE,  Tables.IMAGE_NAME));

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

        String[] strings = new String[2];
        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(preparedStatementHashMap.get("getLoginAndPass"));
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
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
        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(preparedStatementHashMap.get("updateSessionKey"));
            preparedStatement.setString(1, sessionKey);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized void addImage(){
//        SQLBuilder.getInstance().addInsertQuery(Tables.IMAGE, new String[]{Tables.IMAGE_NAME, })
    }

    /**
     * @param id
     * @return <pre>
     *  {@code strings[0] = rs.getString(Tables.USER_NAME);
     *  strings[1] = rs.getString(Tables.USER_PERMISSIONLEVEL);
     *  strings[2] = rs.getString(Tables.USER_SESSION);
     *  strings[3] = rs.getString(Tables.USER_COUNTRY);
    }</pre>
     */
    public synchronized String[] getAuthUser(int id){

        String[] strings = new String[4];
        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(preparedStatementHashMap.get("getAuthUser"));
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                strings[0] = rs.getString(Tables.USER_NAME);
                strings[1] = rs.getString(Tables.USER_PERMISSIONLEVEL);
                strings[2] = rs.getString(Tables.USER_SESSION);
                strings[3] = rs.getString(Tables.USER_COUNTRY);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return strings;
    }

    public synchronized boolean hasImageName(String name){

        try{
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(preparedStatementHashMap.get("hasImageName"));
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                return rs.getInt(1) == 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    private synchronized String generateString(){
        String str = Long.toHexString(Double.doubleToLongBits(Math.random()));
        if (hasImageName(str)){
            generateString();
        }else{
            return str;
        }
        return generateString();
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
