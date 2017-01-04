package com.mrheadshot62.api.types;

import com.mrheadshot62.api.iLogic.IPacket;

import java.io.Serializable;


/**
 * Created by DmitriyRoot on 04.01.2017.
 */
public class AuthPacket implements IPacket, Serializable {
    private final String login;
    private final String pass;

    public AuthPacket(String login, String pass) {
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