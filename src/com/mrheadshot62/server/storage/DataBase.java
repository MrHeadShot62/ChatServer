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

    private static final String url = "jdbc:mysql://localhost:3306/BlueBear";
    private static final String user = "root";
    private static final String password = "";

    private Connection con;
    private Statement stmt;
    private ResultSet rs;

    void addServerUser(String name) throws SQLException {
        stmt.execute("INSERT INTO BlueBear.users (name) VALUES ('"+name+"');");
    }

    private void connect() throws SQLException {
        con = DriverManager.getConnection(url, user, password);
        stmt = con.createStatement();
    }

    public static void main(String args[]) {
        DataBase db = new DataBase();
        try {
            db.connect();
            db.addServerUser("name");
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            db.disconnect();
        }
    }

    private void disconnect(){
        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
//        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
    }
}
