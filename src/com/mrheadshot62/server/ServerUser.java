package com.mrheadshot62.server;

import com.mrheadshot62.api.types.UserPacket;

/**
 * Created by novak on 04.01.2017.
 */
public class ServerUser {
    private UserPacket user;
    private int id;

    public ServerUser(UserPacket user, int id) {
        this.user = user;
    }

    public UserPacket getUser() {
        return user;
    }

    public int getId() {
        return id;
    }
}
