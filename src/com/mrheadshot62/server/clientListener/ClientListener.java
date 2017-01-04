package com.mrheadshot62.server.clientListener;

import com.mrheadshot62.api.types.CommandPacket;
import com.mrheadshot62.api.types.ImagePacket;
import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.types.UserPacket;
import com.mrheadshot62.server.Client;

/**
 * Created by novak on 04.01.2017.
 */
public class ClientListener extends AbstractClientListener{

    public ClientListener(Client client) {
        super(client);
    }

    @Override
    protected void onReceivePacket(Packet p) {
        super.onReceivePacket(p);
    }

    @Override
    protected void onReceiveImage(ImagePacket image) {

    }

    @Override
    protected void onReceiveCommand(CommandPacket command) {

    }

    @Override
    protected void onReceivedUser(UserPacket user) {

    }

    @Override
    protected void onClientDisconnected(Client client) {

    }
}