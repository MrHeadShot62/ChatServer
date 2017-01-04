package com.mrheadshot62.server;


import com.mrheadshot62.api.types.Image;
import com.mrheadshot62.server.serverListener.ServerListener;
import com.mrheadshot62.server.storage.ServerStorage;

public class ServerController {
    public static int PORT = 5555;
    private ServerStorage serverStorage;
    private ServerListener serverListener;
    private boolean isStarted=false;


    public ServerController() {
        this.serverStorage = new ServerStorage();
        this.serverListener = new ServerListener(serverStorage){
            @Override
            protected void onServerStarted() {
                super.onServerStarted();
                isStarted=true;
            }

            @Override
            protected void onServerStopped() {
                super.onServerStopped();
                isStarted=false;
            }
        };
    }

    public void startServer() {
        if (isStarted)System.out.println("Server already started");
        this.serverListener.start();
    }

    public void stopServer() {
        if (!isStarted) System.out.println("Server already stopped");
        this.serverListener.interrupt();
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void sendImage(Image image){
        serverListener.sendImage(image);
    }

    public void sendImage(Image image, int id){
        serverListener.sendImage(image, id);
    }
}
