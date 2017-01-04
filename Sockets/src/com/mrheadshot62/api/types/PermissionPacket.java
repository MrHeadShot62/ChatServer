package com.mrheadshot62.api.types;

/**
 * Created by DmitriyRoot on 04.01.2017.
 */
public class PermissionPacket {
    private final String sessionKey;

    public PermissionPacket(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getSessionKey() {
        return sessionKey;
    }
}
