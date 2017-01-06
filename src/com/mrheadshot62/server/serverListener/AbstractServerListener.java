package com.mrheadshot62.server.serverListener;

import com.mrheadshot62.api.MultiPacket;
import com.mrheadshot62.api.types.ImagePacket;
import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.Types;
import com.mrheadshot62.server.Client;
import com.mrheadshot62.server.ServerController;
import com.mrheadshot62.server.clientListener.ClientListener;
import com.mrheadshot62.server.storage.ServerStorage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

abstract class AbstractServerListener extends Thread{
    protected ServerStorage storage;

    AbstractServerListener(ServerStorage storage){
        this.storage = storage;
    }

    @Override
    public void run() {
        try {
            onServerStarted();
            ServerSocket serverSocket = new ServerSocket(ServerController.PORT);
            while (!serverSocket.isClosed()){
                try {
                    Socket socket = serverSocket.accept();
                    System.out.println(socket.getInetAddress().getHostAddress());
                    Client client = new Client(socket, storage);
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
            onServerStopped();
        }
    }

    private void sendMultiPacket(MultiPacket p, int id){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    storage.getClient(id).getOutput().writeMultiPacket(p);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }



    protected void onListenerAttached(ClientListener clientListener){

    }

    protected void onServerStarted(){

    }

    protected void onServerStopped(){

    }

    protected abstract void onClientConnected(Client c);
    protected abstract void onClientDisconnected(Client c);
}
