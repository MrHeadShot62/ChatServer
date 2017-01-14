package com.mrheadshot62.api.types.answer;

import com.mrheadshot62.api.types.User;

import java.io.Serializable;

/**
 * Created by novak on 13.01.2017.
 */
public class ServerAnswerUserPacket implements Serializable{
    private User user;

    public ServerAnswerUserPacket(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
