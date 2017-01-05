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

    private static final String url = "jdbc:mysql://localhost:3306/qq";
    private static final String user = "root";
    private static final String password = "";

    private Connection con;
    private Statement stmt;
    private ResultSet rs;

    void addServerUser() {
        try {
            rs = stmt.executeQuery("INSERT ");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            disconnect();
        }
    }

    private void connect(){
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();

            rs = stmt.executeQuery(query);
            while (rs.next()) {
                int count = rs.getInt(1);
                System.out.println("Total number of books in the table : " + count);
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public static void main(String args[]) {
        String query = "select count(*) from books";


    }

    private void disconnect(){
        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
    }
}
