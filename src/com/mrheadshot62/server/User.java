package com.mrheadshot62.server;



/**
 * Created by novak on 04.01.2017.
 */
public class User {
    private String login;
    private String pass;
    private String session;
    private String name;
    private int id;

    public User(String login, String pass, String session, String name, int id) {

        this.login = login;
        this.pass = pass;
        this.session = session;
        this.name = name;
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String getSession() {
        return session;
    }

    public String getName() {
        return name;
    }

    public int getId(){
        return id;
    }
}
