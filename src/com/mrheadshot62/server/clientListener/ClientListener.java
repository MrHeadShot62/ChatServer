package com.mrheadshot62.server.clientListener;

import com.mrheadshot62.api.MultiPacket;
import com.mrheadshot62.api.types.AuthPacket;
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
    protected void onReceiveMultiPacket(MultiPacket p) {
        super.onReceiveMultiPacket(p);
    }

    @Override
    protected void onReceiveImagePacket(ImagePacket image) {

    }

    @Override
    protected void onReceiveAuthPacket(AuthPacket auth) {

    }

    @Override
    protected void onReceiveCommandPacket(CommandPacket command) {

    }

    @Override
    protected void onReceiveUserPacket(UserPacket user) {

    }

    @Override
    protected void onClientDisconnected(Client client) {

    }
}