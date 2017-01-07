package com.mrheadshot62.server.handler;

import com.mrheadshot62.api.types.UserPacket;
import com.mrheadshot62.server.handler.abstracts.AUserHandler;

class UserHandler extends AUserHandler {

    public UserHandler(UserPacket p, int id) {
        super(p, id);
    }

    @Override
    protected void handleCommandPacket(UserPacket userPacket) {
        System.out.println("UserPacket received");
    }
}
