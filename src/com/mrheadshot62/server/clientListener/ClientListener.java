package com.mrheadshot62.server.clientListener;

import com.mrheadshot62.api.types.Command;
import com.mrheadshot62.api.types.Image;
import com.mrheadshot62.api.types.Packet;
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
    protected void onReceiveImage(Image image) {

    }

    @Override
    protected void onReceiveCommand(Command command) {

    }

    @Override
    protected void onClientDisconnected(Client client) {

    }
}