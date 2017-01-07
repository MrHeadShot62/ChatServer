package com.mrheadshot62.api.types;

import java.io.Serializable;

/**
 * Created by DmitriyRoot on 04.01.2017.
 */
public class PermissionPacket extends CorePacket implements Serializable{

    private final String sessionKey;

    private final int permissionLevel;

    private final int userID;

    public PermissionPacket(String sessionKey, int permissionLevel, int userID) {
        super();
        this.sessionKey = sessionKey;
        this.permissionLevel = permissionLevel;
        this.userID = userID;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public int getUserID() {
        return userID;
    }
}
