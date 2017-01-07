package com.mrheadshot62.server;



/**
 * Created by novak on 04.01.2017.
 */
public class ServerUser {
    private String login;
    private String pass;
    private String session;
    private int id;
    private String user;
    public ServerUser(int id, int login, String name) {

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getId(){
        return id;
    }
}
