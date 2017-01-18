package com.mrheadshot62.api.types;

import java.io.Serializable;

/**
 * Created by novak on 18.01.2017.
 */
public class AuthReadyUser extends CorePacket implements Serializable {
    private final String key;
    private final int id;
    private final boolean isReg;
    public AuthReadyUser(String key, int id, boolean isReg) {
        this.key = key;
        this.id = id;
        this.isReg = isReg;
    }

    public String getKey() {
        return key;
    }

    public boolean isReg() {
        return isReg;
    }

    @Override
    public int getId() {
        return id;
    }
}
