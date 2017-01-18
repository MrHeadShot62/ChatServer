package com.mrheadshot62.server.storage;

import com.mrheadshot62.server.Client;
import com.mrheadshot62.api.types.User;

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
        preparedStatementHashMap.put("addUser",String.format("INSERT INTO %s(`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", Tables.USER, Tables.USER_LOGIN, Tables.USER_FNAME, Tables.USER_LNAME, Tables.USER_NICK, Tables.USER_AGE, Tables.USER_IS_UPDATED, Tables.USER_COUNTRY, Tables.USER_CITY, Tables.USER_RATING, Tables.USER_PHOTOS, Tables.USER_PROFILE_PHOTO, Tables.USER_LAST_ONLINE, Tables.USER_EMAIL, Tables.USER_FRIENDS, Tables.USER_PERMISSIONLVL, Tables.USER_COUNT_PHOTO, Tables.USER_BALANCE, Tables.USER_ONLINE, Tables.USER_REGISTRATION,Tables.USER_LAST_AUTH));
        preparedStatementHashMap.put("hasImageName",String.format("SELECT 0 FROM %s WHERE %s = ?", Tables.IMAGE,  Tables.IMAGE_NAME));
        preparedStatementHashMap.put("updateUser", String.format("UPDATE %s SET `%s`=?,`%s`=?,`%s`=?,`%s`=?,`%s`=?,`%s`=?,`%s`=?,`%s`=?,`%s`=?,`%s`=?,`%s`=?,`%s`=?,`%s`=?,`%s`=?,`%s`=?,`%s`=?,`%s`=?,`%s`=?,`%s`=?,`%s`=? WHERE `id`=?", Tables.USER, Tables.USER_LOGIN, Tables.USER_FNAME, Tables.USER_LNAME, Tables.USER_NICK, Tables.USER_AGE, Tables.USER_IS_UPDATED, Tables.USER_COUNTRY, Tables.USER_CITY, Tables.USER_RATING, Tables.USER_PHOTOS, Tables.USER_PROFILE_PHOTO, Tables.USER_LAST_ONLINE, Tables.USER_EMAIL, Tables.USER_FRIENDS, Tables.USER_PERMISSIONLVL, Tables.USER_COUNT_PHOTO, Tables.USER_BALANCE, Tables.USER_ONLINE, Tables.USER_REGISTRATION,Tables.USER_LAST_AUTH));
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

    public void updateUser(User u){
        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(preparedStatementHashMap.get("updateUser"));
            preparedStatement.setString(1, Tables.USER);
            preparedStatement.setString(2, u.getLogin());
            preparedStatement.setString(3, u.getFname());
            preparedStatement.setString(4, u.getLname());
            preparedStatement.setString(5, u.getNickname());
            preparedStatement.setInt(6, u.getAge());
            preparedStatement.setInt(7, u.getIsUpdated());
            preparedStatement.setInt(8, u.getContry());
            preparedStatement.setInt(9, u.getCity());
            preparedStatement.setInt(10, u.getRating());
            preparedStatement.setString(11, u.getPhotos());
            preparedStatement.setString(12, u.getProfilePhotos());
            preparedStatement.setTimestamp(13, u.getLastoOnline());
            preparedStatement.setString(14, u.getEmail());
            preparedStatement.setString(15, u.getFriends());
            preparedStatement.setInt(16, u.getPermissionLvl());
            preparedStatement.setInt(17, u.getCounPhoto());
            preparedStatement.setInt(18, u.getBalance());
            preparedStatement.setInt(19, (int)u.getOnline());
            preparedStatement.setTimestamp(20, u.getRegistration());
            preparedStatement.setTimestamp(21, u.getLastAuth());
            preparedStatement.setInt(22, u.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addUser(User u){
        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(preparedStatementHashMap.get("addUser"));
            preparedStatement.setString(1, Tables.USER);
            preparedStatement.setString(2, u.getLogin());
            preparedStatement.setString(3, u.getFname());
            preparedStatement.setString(4, u.getLname());
            preparedStatement.setString(5, u.getNickname());
            preparedStatement.setInt(6, u.getAge());
            preparedStatement.setInt(7, u.getIsUpdated());
            preparedStatement.setInt(8, u.getContry());
            preparedStatement.setInt(9, u.getCity());
            preparedStatement.setInt(10, u.getRating());
            preparedStatement.setString(11, u.getPhotos());
            preparedStatement.setString(12, u.getProfilePhotos());
            preparedStatement.setTimestamp(13, u.getLastoOnline());
            preparedStatement.setString(14, u.getEmail());
            preparedStatement.setString(15, u.getFriends());
            preparedStatement.setInt(16, u.getPermissionLvl());
            preparedStatement.setInt(17, u.getCounPhoto());
            preparedStatement.setInt(18, u.getBalance());
            preparedStatement.setInt(19, (int)u.getOnline());
            preparedStatement.setTimestamp(20, u.getRegistration());
            preparedStatement.setTimestamp(21, u.getLastAuth());
            preparedStatement.setInt(22, u.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUser(int id){
        User user=null;
        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(preparedStatementHashMap.get("getUser"));
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                 user = new User(rs.getInt("id"),
                         rs.getString(Tables.USER_LOGIN),
                         rs.getString(Tables.USER_FNAME),
                         rs.getString(Tables.USER_LNAME),
                         rs.getString(Tables.USER_FRIENDS),
                         rs.getString(Tables.USER_NICK),
                         rs.getString(Tables.USER_PHOTOS),
                         rs.getString(Tables.USER_PROFILE_PHOTO),
                         rs.getString(Tables.USER_EMAIL),
                         rs.getInt(Tables.USER_PERMISSIONLVL),
                         rs.getInt(Tables.USER_AGE),
                         rs.getInt(Tables.USER_COUNTRY),
                         rs.getInt(Tables.USER_CITY),
                         rs.getInt(Tables.USER_RATING),
                         rs.getInt(Tables.USER_IS_UPDATED),
                         rs.getInt(Tables.USER_COUNT_PHOTO),
                         rs.getInt(Tables.USER_BALANCE),
                         (byte)rs.getInt(Tables.USER_ONLINE),
                         rs.getTimestamp(Tables.USER_LAST_ONLINE),
                         rs.getTimestamp(Tables.USER_REGISTRATION),
                         rs.getTimestamp(Tables.USER_LAST_AUTH));
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
