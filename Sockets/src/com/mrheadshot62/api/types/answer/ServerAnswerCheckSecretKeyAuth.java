package com.mrheadshot62.api.types.answer;

import java.io.Serializable;

/**
 * Created by novak on 18.01.2017.
 */
public class ServerAnswerCheckSecretKeyAuth extends CoreServerAnswerPacket implements Serializable{
    private final int id;
    private final boolean toRegister;

    public ServerAnswerCheckSecretKeyAuth(int id, boolean toRegister) {
        this.id = id;
        this.toRegister = toRegister;
    }

    public boolean isToRegister() {
        return toRegister;
    }

    public int getId() {
        return id;
    }
}
