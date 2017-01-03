package com.mrheadshot62.server.serverListener;

import com.mrheadshot62.api.types.Image;
import com.mrheadshot62.api.types.Packet;
import com.mrheadshot62.api.types.Types;
import com.mrheadshot62.server.Client;
import com.mrheadshot62.server.ServerController;
import com.mrheadshot62.server.clientListener.ClientListener;
import com.mrheadshot62.server.storage.ServerStorage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by novak on 04.01.2017.
 */
abstract class AbstractServerListener extends Thread{
    private ServerStorage storage;

    public AbstractServerListener(ServerStorage storage){
        this.storage = storage;
    }

    @Override
    public void run() {
        try {
            onThreadStarted();
            ServerSocket serverSocket = new ServerSocket(ServerController.PORT);
            while (!serverSocket.isClosed()){
                try {
                    Socket socket = serverSocket.accept();
                    Client client = new Client(socket);
                    onClientConnected(client);
                    storage.addClient(client);
                    ClientListener lisener = new ClientListener(client);
                    onListenerAttached(lisener);
                    AbstractServerListener abstractServerListener = this;
                    new ClientListener(client){
                        @Override
                        protected void onClientDisconnected(Client client) {
                            super.onClientDisconnected(client);
                            abstractServerListener.onClientDisconnected(client);
                        }
                    };
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void sendPacket(Packet p, int id) throws IOException {
        storage.getClient(id).getOutput().writePacket(p);
    }

    public void sendImage(Image image, int id) throws IOException{
        sendPacket(new Packet(image, Types.Image), id);
    }



    protected void onListenerAttached(ClientListener clientListener){

    }

    protected void onThreadStarted(){

    }

    protected abstract void onClientConnected(Client c);
    protected abstract void onClientDisconnected(Client c);
}
