package com.mrheadshot62.server.serverListener;

import com.mrheadshot62.api.types.Image;
import com.mrheadshot62.server.Client;
import com.mrheadshot62.server.clientListener.ClientListener;
import com.mrheadshot62.server.storage.ServerStorage;

import java.io.IOException;

/**
 * Created by novak on 04.01.2017.
 */
public class ServerListener extends AbstractServerListener {
    public ServerListener(ServerStorage storage) {
        super(storage);
    }

    public void sendImage(Image image){
        if (image==null){
            System.out.println("Null image");
            return;
        }
        for (Client c:storage.getClients()){
            sendImage(image, c.getId());
        }
    }



    @Override
    protected void onListenerAttached(ClientListener clientListener) {
        super.onListenerAttached(clientListener);
    }

    @Override
    protected void onServerStarted() {
        super.onServerStarted();
        System.out.println("Server Started!");
    }

    @Override
    protected void onServerStopped() {
        super.onServerStopped();
        System.out.println("Server Stopped!");
    }

    @Override
    protected void onClientConnected(Client c) {
        System.out.printf("Client #%d connected!%n", c.getId());
    }

    @Override
    protected void onClientDisconnected(Client c) {
        System.out.printf("Client #%d disconnected!%n", c.getId());
    }
}