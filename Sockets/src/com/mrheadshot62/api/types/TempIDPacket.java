package com.mrheadshot62.api.types;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by novak on 19.01.2017.
 */
public class TempIDPacket extends CorePacket implements Serializable{
    private UUID uuid;

    public TempIDPacket(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }
}
