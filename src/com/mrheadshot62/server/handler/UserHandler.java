package com.mrheadshot62.server.handler;

import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.types.UserPacket;
import com.mrheadshot62.server.handler.abstracts.AUserHandler;

/**
 * Created by novak on 05.01.2017.
 */
public class UserHandler extends AUserHandler {

    public UserHandler(UserPacket p) {
        super(p);
    }

    @Override
    protected void handleCommandPacket(UserPacket userPacket) {

    }
}
