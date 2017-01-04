package com.mrheadshot62.server;

import com.mrheadshot62.api.types.User;

/**
 * Created by novak on 04.01.2017.
 */
public class ServerUser {
    private User user;
    private int id;

    public ServerUser(User user, int id) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public int getId() {
        return id;
    }
}
