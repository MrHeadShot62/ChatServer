package com.mrheadshot62.api.types;

import java.io.Serializable;


/**
 * Created by DmitriyRoot on 04.01.2017.
 */
public class AuthPacket extends CorePacket implements Serializable {
    private final String login;
    private final String pass;

    public AuthPacket(String login, String pass) {
        super();
        this.login = login;
        this.pass = pass;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

}