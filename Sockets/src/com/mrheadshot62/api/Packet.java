package com.mrheadshot62.api;

import java.io.Serializable;

/**
 * Created by novak on 04.01.2017.
 */
public class Packet implements Serializable{
    private Object data;
    private int type;

    public Packet(Object data, int type) {
        this.data = data;
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public int getType() {
        return type;
    }
}
