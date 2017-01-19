package com.mrheadshot62.server.storage;

import com.mrheadshot62.api.types.UserDatas;
import com.mrheadshot62.server.Client;

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
        preparedStatementHashMap.put("getUserDatas",String.format("SELECT * FROM %s WHERE `id` = ?", Tables.USER));
        preparedStatementHashMap.put("checkEmail",String.format("SELECT `id` FROM %s WHERE `email` = ?", Tables.USER));
        preparedStatementHashMap.put("addUser",String.format("INSERT INTO %s(`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", Tables.USER, Tables.USER_LOGIN, Tables.USER_FNAME, Tables.USER_LNAME, Tables.USER_NICK, Tables.USER_AGE, Tables.USER_IS_UPDATED, Tables.USER_COUNTRY, Tables.USER_CITY, Tables.USER_RATING, Tables.USER_PHOTOS, Tables.USER_PROFILE_PHOTO, Tables.USER_LAST_ONLINE, Tables.USER_EMAIL, Tables.USER_FRIENDS, Tables.USER_PERMISSIONLVL, Tables.USER_COUNT_PHOTO, Tables.USER_BALANCE, Tables.USER_ONLINE, Tables.USER_REGISTRATION,Tables.USER_LAST_AUTH));
        preparedStatementHashMap.put("hasImageName",String.format("SELECT 0 FROM %s WHERE %s = ?", Tables.IMAGE,  Tables.IMAGE_NAME));
        preparedStatementHashMap.put("updateUser", String.format("UPDATE %s SET `%s`=?,`%s`=?,`%s`=?,`%s`=?,`%s`=?,`%s`=?,`%s`=?,`%s`=?,`%s`=?,`%s`=?,`%s`=?,`%s`=?,`%s`=?,`%s`=?,`%s`=?,`%s`=?,`%s`=?,`%s`=?,`%s`=?,`%s`=? WHERE `id`=?", Tables.USER, Tables.USER_LOGIN, Tables.USER_FNAME, Tables.USER_LNAME, Tables.USER_NICK, Tables.USER_AGE, Tables.USER_IS_UPDATED, Tables.USER_COUNTRY, Tables.USER_CITY, Tables.USER_RATING, Tables.USER_PHOTOS, Tables.USER_PROFILE_PHOTO, Tables.USER_LAST_ONLINE, Tables.USER_EMAIL, Tables.USER_FRIENDS, Tables.USER_PERMISSIONLVL, Tables.USER_COUNT_PHOTO, Tables.USER_BALANCE, Tables.USER_ONLINE, Tables.USER_REGISTRATION,Tables.USER_LAST_AUTH));
    }

    public boolean checkEmail(String email){
        try{
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(preparedStatementHashMap.get("getLoginAndPass"));
            preparedStatement.setString(1, email);
            return preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public synchronized static ServerStorage getInstance() {
        if (instance==null){
            instance = new ServerStorage();
            return instance;
        }else{
            return instance;
        }
    }

    public synchronized Set<Map.Entry<String,Client>> getClients(){
        return storage.getClients();
    }

    public synchronized Map<String, Client> getMap(){
        return storage.getMap();
    }

    public synchronized void addClient(Client client){
        storage.getMap().put(client.getId(), client);
    }

    public synchronized Client getClient(int id){
        return storage.getMap().get(id);
    }

    public synchronized void removeClient(int id){
        for (Map.Entry<String, Client> map:getClients()){
            if (map.getValue().getId().equals(String.valueOf(id))){
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

    public void updateUser(UserDatas u){
        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(preparedStatementHashMap.get("updateUser"));
            preparedStatement.setString(1, u.getLogin());
            preparedStatement.setString(2, u.getFname());
            preparedStatement.setString(3, u.getLname());
            preparedStatement.setString(4, u.getNickname());
            preparedStatement.setInt(5, u.getAge());
            preparedStatement.setInt(6, u.getIsUpdated());
            preparedStatement.setInt(7, u.getContry());
            preparedStatement.setInt(8, u.getCity());
            preparedStatement.setInt(9, u.getRating());
            preparedStatement.setString(10, u.getPhotos());
            preparedStatement.setString(11, u.getProfilePhotos());
            preparedStatement.setTimestamp(12, u.getLastoOnline());
            preparedStatement.setString(13, u.getEmail());
            preparedStatement.setString(14, u.getFriends());
            preparedStatement.setInt(15, u.getPermissionLvl());
            preparedStatement.setInt(16, u.getCounPhoto());
            preparedStatement.setInt(17, u.getBalance());
            preparedStatement.setInt(18, (int)u.getOnline());
            preparedStatement.setTimestamp(19, u.getRegistration());
            preparedStatement.setTimestamp(20, u.getLastAuth());
            preparedStatement.setInt(21, u.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addUser(UserDatas u){
        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(preparedStatementHashMap.get("addUser"));
            preparedStatement.setString(1, u.getLogin());
            preparedStatement.setString(2, u.getFname());
            preparedStatement.setString(3, u.getLname());
            preparedStatement.setString(4, u.getNickname());
            preparedStatement.setInt(5, u.getAge());
            preparedStatement.setInt(6, u.getIsUpdated());
            preparedStatement.setInt(7, u.getContry());
            preparedStatement.setInt(8, u.getCity());
            preparedStatement.setInt(9, u.getRating());
            preparedStatement.setString(10, u.getPhotos());
            preparedStatement.setString(11, u.getProfilePhotos());
            preparedStatement.setTimestamp(12, u.getLastoOnline());
            preparedStatement.setString(13, u.getEmail());
            preparedStatement.setString(14, u.getFriends());
            preparedStatement.setInt(15, u.getPermissionLvl());
            preparedStatement.setInt(16, u.getCounPhoto());
            preparedStatement.setInt(17, u.getBalance());
            preparedStatement.setInt(18, (int)u.getOnline());
            preparedStatement.setTimestamp(19, u.getRegistration());
            preparedStatement.setTimestamp(20, u.getLastAuth());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int addGoogleUser(String email){
        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(preparedStatementHashMap.get("addUser"));
            preparedStatement.setString(1, "2");
            preparedStatement.setString(2, "2");
            preparedStatement.setString(3, "2");
            preparedStatement.setString(4, "2");
            preparedStatement.setInt(5, 0);
            preparedStatement.setInt(6, 0);
            preparedStatement.setInt(7, 0);
            preparedStatement.setInt(8, 0);
            preparedStatement.setInt(9, 0);
            preparedStatement.setString(10, "");
            preparedStatement.setString(11, "");
            preparedStatement.setTimestamp(12, null);
            preparedStatement.setString(13, email);
            preparedStatement.setString(14,"");
            preparedStatement.setInt(15, 0);
            preparedStatement.setInt(16, 0);
            preparedStatement.setInt(17, 0);
            preparedStatement.setInt(18, 0);
            preparedStatement.setTimestamp(19, null);
            preparedStatement.setTimestamp(20, null);
            preparedStatement.execute();

            PreparedStatement preparedStatement2 = Connector.getConnection().prepareStatement(preparedStatementHashMap.get("checkEmail"));
            preparedStatement2.setString(1, email);
            ResultSet rs2 = preparedStatement2.executeQuery();
            try {
                while (rs2.next()) {
                    return rs2.getInt("id");
                }
            }catch (Exception e){
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public UserDatas getUser(int id){
        UserDatas userDatas =null;
        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(preparedStatementHashMap.get("getUserDatas"));
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                 userDatas = new UserDatas(rs.getInt("id"),
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
        return userDatas;
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
