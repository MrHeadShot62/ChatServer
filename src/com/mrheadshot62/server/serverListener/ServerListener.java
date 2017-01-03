package com.mrheadshot62.server.serverListener;

import com.mrheadshot62.server.Client;
import com.mrheadshot62.server.storage.ServerStorage;

/**
 * Created by novak on 04.01.2017.
 */
public class ServerListener extends AbstractServerListener {
    public ServerListener(ServerStorage storage) {
        super(storage);
    }

    @Override
    protected void onClientConnected(Client c) {

    }

    @Override
    protected void onClientDisconnected(Client c) {

    }
}
