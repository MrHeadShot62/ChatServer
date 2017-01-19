package com.mrheadshot62.server;


import com.mrheadshot62.api.Types;
import com.mrheadshot62.server.handlers.AuthHandler;
import com.mrheadshot62.server.handlers.MainHandler;
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
        if (isStarted){
            System.out.println("Server already started");
        }else{
            this.serverListener.start();
            addHandlers();
        }
    }

    public void addHandlers(){
        MainHandler.addHandler(new AuthHandler(Types.AUTH_FIRST_STEP));
    }

    public void stopServer() {
        if (!isStarted) System.out.println("Server already stopped");
        this.serverListener.interrupt();
    }

    public boolean isStarted() {
        return isStarted;
    }
}
