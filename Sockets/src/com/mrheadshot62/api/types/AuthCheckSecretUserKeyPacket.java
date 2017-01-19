package com.mrheadshot62.api.types;

import java.io.Serializable;

/**
 * Created by novak on 18.01.2017.
 */
public class AuthCheckSecretUserKeyPacket extends CorePacket implements Serializable {
    private final String key;
    private final int id;
    public AuthCheckSecretUserKeyPacket(String key, int id) {
        this.key = key;
        this.id = id;
    }

    public String getKey() {
        return key;
    }


    @Override
    public int getId() {
        return id;
    }
}
