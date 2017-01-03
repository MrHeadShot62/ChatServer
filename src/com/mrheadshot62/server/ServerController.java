package com.mrheadshot62.server;


import com.mrheadshot62.server.storage.ServerStorage;

public class ServerController {
    public static int PORT = 5555;
    private ServerStorage serverStorage;

    public ServerController() {
        this.serverStorage = new ServerStorage();
    }

    public void startServer() {
    }

    public void stopServer() {
    }

    public String getStatus() {
        return null;
    }
}
