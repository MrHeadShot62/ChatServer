package com.mrheadshot62.api.types;

import java.io.Serializable;

/**
 * Created by DmitriyRoot on 04.01.2017.
 */
public class PermissionPacket implements Serializable{

    private final String sessionKey;

    private final int permissionLevel;

    public PermissionPacket(String sessionKey, int permissionLevel) {
        this.sessionKey = sessionKey;
        this.permissionLevel = permissionLevel;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }
}
