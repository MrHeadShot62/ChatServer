package com.mrheadshot62.api.types;

import java.io.Serializable;


/**
 * Created by DmitriyRoot on 04.01.2017.
 */
public class AuthPacket extends CorePacket implements Serializable {
    private final byte type;
    private final String[] strings;
    public AuthPacket(byte type, String... arg) {
        super();
        this.type = type;
        this.strings = arg;
    }

    public byte getType() {
        return type;
    }

    public String[] getArgs() {
        return strings;
    }
}