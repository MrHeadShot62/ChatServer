package com.mrheadshot62.api.types;

import java.io.Serializable;

/**
 * Created by novak on 18.01.2017.
 */
public class AuthRegistrationPacket extends CorePacket implements Serializable {
    private final Object[] data;
    private final byte type;

    public AuthRegistrationPacket(byte type, Object... data) {
        this.data = data;
        this.type = type;
    }

    public byte getType() {
        return type;
    }

    public Object[] getData() {
        return data;
    }
}
