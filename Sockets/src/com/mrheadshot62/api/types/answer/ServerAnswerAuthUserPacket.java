package com.mrheadshot62.api.types.answer;

import com.mrheadshot62.api.types.User;

import java.io.Serializable;

/**
 * Created by novak on 13.01.2017.
 */
public class ServerAnswerAuthUserPacket implements Serializable{
    private User user;
    private String session;

    public ServerAnswerAuthUserPacket(String session, User user) {
        this.session = session;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getSession() {
        return session;
    }
}
