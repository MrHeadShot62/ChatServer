package com.mrheadshot62.server.storage;

import com.mrheadshot62.server.Client;
import com.mrheadshot62.server.ServerUser;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by novak on 04.01.2017.
 */
class DataBase {
    private ArrayList<Client> clients;
    private ArrayList<ServerUser> serverUsers;
    ArrayList<Client> getClients() {
        return clients;
    }

    public ArrayList<ServerUser> getServerUsers() {
        return serverUsers;
    }
    ////////////////////////////////////////////////////////////////////

    private static final String url = "jdbc:mysql://localhost:3306/BlueBear";
    private static final String user = "root";
    private static final String password = "";

    private Connection con;
    private Statement stmt;
    private ResultSet rs;

    void addServerUser(String name) {
        try {
//            PreparedStatement stmtp = con.prepareStatement();
            stmt.executeQuery("INSERT INTO BlueBear.users (name) VALUES ("+name+");");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            disconnect();
        }
    }

    private void connect(){
        try {
            con = DriverManager.getConnection(url, user, password);
//            rs = stmt.executeQuery(query);
//            while (rs.next()) {
//                int count = rs.getInt(1);
//                System.out.println("Total number of books in the table : " + count);
//            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public static void main(String args[]) {
        DataBase db = new DataBase();
        db.connect();
        db.addServerUser("name");
    }

    private void disconnect(){
//        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
//        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
//        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
    }
}
