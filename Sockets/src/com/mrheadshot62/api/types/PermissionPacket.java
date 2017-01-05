package com.mrheadshot62.api.types;


import com.mrheadshot62.api.PermissionLevel;

/**
 * Created by DmitriyRoot on 04.01.2017.
 */
public class PermissionPacket {

    private final String sessionKey;

    private final PermissionLevel permissionLevel;

    public PermissionPacket(String sessionKey, PermissionLevel permissionLevel) {
        this.sessionKey = sessionKey;
        this.permissionLevel = permissionLevel;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public PermissionLevel getPermissionLevel() {
        return permissionLevel;
    }
}
