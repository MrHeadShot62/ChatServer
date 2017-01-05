package com.mrheadshot62.api.types;

import java.io.Serializable;

/**
 * Created by novak on 04.01.2017.
 */
public class UserPacket implements Serializable {
    private String name;

    public UserPacket(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}