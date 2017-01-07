package com.mrheadshot62.api.types;


import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by DmitriyRoot on 07.01.2017.
 */
public abstract class CorePacket implements Serializable{
    private int id;

    public CorePacket() {

    }
    public int getId() {
        return id;
    }
}
