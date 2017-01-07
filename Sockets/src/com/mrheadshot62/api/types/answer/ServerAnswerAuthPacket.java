package com.mrheadshot62.api.types.answer;

/**
 * Created by DmitriyRoot on 07.01.2017.
 */
public class ServerAnswerAuthPacket {

    private final String nameUser;
    private final String countryUser;
    private final String sessionKey;
    private final int permission;

    public ServerAnswerAuthPacket(String nameUser, String countryUser, String sessionKey, int permission) {
        this.nameUser = nameUser;
        this.countryUser = countryUser;
        this.sessionKey = sessionKey;
        this.permission = permission;
    }

    public String getNameUser() {
        return nameUser;
    }

    public String getCountryUser() {
        return countryUser;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public int getPermission() {
        return permission;
    }
}
