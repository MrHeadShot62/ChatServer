package com.mrheadshot62.server.storage;

import com.mrheadshot62.api.PermissionLevel;
import com.mrheadshot62.server.Client;
import com.mrheadshot62.server.ServerUser;
import com.sun.istack.internal.Nullable;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by novak on 04.01.2017.
 */
class Storage {
    private ArrayList<Client> clients=new ArrayList<>();
    private ArrayList<ServerUser> serverUsers=new ArrayList<>();
    ArrayList<Client> getClients() {
        return clients;
    }

    public ArrayList<ServerUser> getServerUsers() {
        return serverUsers;
    }
    ////////////////////////////////////////////////////////////////////





    private Connection con;

    //////SESSION
    void changeSessionKey(int id, String key){
//        new SQLBuilder().addUpdateQuery(USER_TABLE, "session", key, "id", String.valueOf(id)).execute();
    }
    String getSessionKey(int id){
//        try {
//            ResultSet resultSet = stmt.executeQuery(String.format("SELECT `session` FROM BlueBear.users WHERE `id` =%d;", id));
//            while (resultSet.next()){
//                return resultSet.getString("session");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return null;
    }
    //////PERMISSION
    void changePerLvl(int id, int perLvl){
//        new SQLBuilder().addUpdateQuery(USER_TABLE, "permissionLevel", String.valueOf(perLvl), "id", String.valueOf(id)).execute();
    }
    int getPerLvl(int id){
//        try {
//            ResultSet resultSet = stmt.executeQuery(String.format("SELECT `permissionLevel` FROM BlueBear.users WHERE `id` =%d;", id));
//            while (resultSet.next()){
//                return resultSet.getInt("permissionLevel");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return 0;
    }
    //////USER
    void getUser(int id){
//        try {
//            ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM BlueBear.users WHERE `id` = %d;", id));
//            while (rs.next())   {
//                String name = rs.getString("name");
//                String login = rs.getString("login");
//                String pass = rs.getString("pass");
//                String session = rs.getString("session");
//                int perLvl = rs.getInt("permissionLevel");
//                String county = rs.getString("country");// TODO
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        System.out.println();
    }
    void addUser(String name, String login, String pass, String session) throws SQLException {
//        stmt.execute(String.format("INSERT INTO BlueBear.users (name, login, pass, session) VALUES ('%s', '%s', '%s', '%s');", name, login, pass, session));
    }
    void deleteUser(int id){
//        try {
//            stmt.execute(String.format("DELETE FROM BlueBear.users WHERE `id` = %d;", id));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
    /////////////////////////////////////////////////






    private void disconnect(){
        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
        //try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
    }


}
