package com.mrheadshot62.server;

import com.mrheadshot62.api.streams.BlueBearInputStream;
import com.mrheadshot62.api.streams.BlueBearOutputStream;
import com.mrheadshot62.api.types.UserPacket;
import com.mrheadshot62.server.storage.ServerStorage;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by novak on 04.01.2017.
 */
public class Client {
    private BlueBearInputStream input;
    private BlueBearOutputStream output;
    private String ip;
    private int id;

    public Client(Socket socket, ServerStorage storage){
        try {
            this.output = new BlueBearOutputStream(socket.getOutputStream());
            this.input = new BlueBearInputStream(socket.getInputStream());
            this.ip = socket.getInetAddress().getHostAddress();
            this.id = storage.getClients().size();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BlueBearInputStream getInput() {
        return input;
    }

    public BlueBearOutputStream getOutput() {
        return output;
    }

    public String getIp() {
        return ip;
    }

    public int getId() {
        return id;
    }
}
