package com.mrheadshot62.server.storage;

import com.mrheadshot62.server.Client;
import com.mrheadshot62.api.types.User;
import com.sun.istack.internal.Nullable;

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
        preparedStatementHashMap.put("getUser",String.format("SELECT * FROM %s WHERE `id` = ?", Tables.USER));
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
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return strings;
    }


    public User getUser(int id){
        User user=null;
        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(preparedStatementHashMap.get("getUser"));
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                 user = new User(rs.getString(Tables.USER_LOGIN),
                        rs.getString(Tables.USER_FNAME),
                        rs.getString(Tables.USER_LNAME),
                        rs.getString(Tables.USER_NICK),
                        rs.getString(Tables.USER_COUNTRY),
                        rs.getString(Tables.USER_CITY),
                        rs.getInt(Tables.USER_COUNT_PHOTO),
                        rs.getString(Tables.USER_PROFILEPHOTO),
                        rs.getString(Tables.USER_LASTONLINE),
                        rs.getString(Tables.USER_EMAIL),
                        rs.getString(Tables.USER_FRIENDS),
                        rs.getString(Tables.USER_SESSION),
                        rs.getInt(Tables.USER_AGE),
                        rs.getInt("id"),
                        rs.getInt(Tables.USER_RATING),
                        rs.getInt(Tables.USER_LASTONLINE));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public synchronized void updateSessionKey(String sessionKey, int id){
        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(preparedStatementHashMap.get("updateSessionKey"));
            preparedStatement.setString(1, sessionKey);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized void addImage(){
//        SQLBuilder.getInstance().addInsertQuery(Tables.IMAGE, new String[]{Tables.IMAGE_NAME, })
    }

    public synchronized boolean hasImageName(String name){

        try{
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(preparedStatementHashMap.get("hasImageName"));
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                return rs.getInt(1) == 0;
            }
            preparedStatement.close();
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
}
