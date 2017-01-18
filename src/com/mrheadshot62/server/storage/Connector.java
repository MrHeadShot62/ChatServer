package com.mrheadshot62.server.storage;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by novak on 09.01.2017.
 */
class Connector {
    private static final String url = "jdbc:mysql://localhost:3306/BlueBear";
    private static final String user = "root";
    private static final String password = "";
    private static Connection con;
    private static boolean isConnected=false;

    private static void connect() {
        try {
            con = DriverManager.getConnection(url, user, password);
            isConnected = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static Connection getConnection(){
        if (!isConnected)connect();
        return con;
    }

}