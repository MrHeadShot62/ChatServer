package com.mrheadshot62.api.types;

import java.io.Serializable;

/**
 * Created by novak on 04.01.2017.
 */
public class User implements Serializable {
    private String name;

    public User(String name, int id) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
