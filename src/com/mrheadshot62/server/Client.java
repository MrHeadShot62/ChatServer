package com.mrheadshot62.server;

import com.mrheadshot62.api.streams.BlueBearInputStream;
import com.mrheadshot62.api.streams.BlueBearOutputStream;
import com.mrheadshot62.api.types.UserPacket;
import com.mrheadshot62.server.storage.ServerStorage;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.UUID;

/**
 * Created by novak on 04.01.2017.
 */
public class Client {
    private BlueBearInputStream input;
    private BlueBearOutputStream output;
    private String id;
    private String ip;

    public Client(Socket socket){
        try {
            this.output = new BlueBearOutputStream(socket.getOutputStream());
            this.input = new BlueBearInputStream(socket.getInputStream());
            this.ip = socket.getInetAddress().getHostAddress();
            this.id = UUID.randomUUID().toString();
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
