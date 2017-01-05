package com.mrheadshot62.server.handler;

import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.types.CommandPacket;
import com.mrheadshot62.server.handler.abstracts.ACommandHandler;

/**
 * Created by novak on 05.01.2017.
 */
class CommandHandler extends ACommandHandler{

    public CommandHandler(CommandPacket p) {
        super(p);
    }

    @Override
    protected void handleCommandPacket(CommandPacket commandPacket) {
        System.out.println("PacketCommand received");
        System.out.println("Command id: "+ commandPacket.getCommand());
    }
}
