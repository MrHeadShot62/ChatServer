package com.mrheadshot62.server;


import com.mrheadshot62.api.types.ImagePacket;
import com.mrheadshot62.server.serverListener.ServerListener;
import com.mrheadshot62.server.storage.ServerStorage;

public class ServerController {
    public static int PORT = 27015;
    private ServerStorage serverStorage;
    private ServerListener serverListener;
    private boolean isStarted=false;


    public ServerController() {
        this.serverStorage = ServerStorage.getInstance();
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
}
