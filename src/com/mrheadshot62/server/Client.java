package com.mrheadshot62.server;

import com.mrheadshot62.api.streams.BlueBearInputStream;
import com.mrheadshot62.api.streams.BlueBearOutputStream;
import com.mrheadshot62.api.types.User;
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
    private InetAddress inetAddress;
    private int id;
    private User user;

    public Client(Socket socket, ServerStorage storage){
        try {
            this.input = new BlueBearInputStream(socket.getInputStream());
            this.output = new BlueBearOutputStream(socket.getOutputStream());
            this.inetAddress = socket.getInetAddress();
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

    public InetAddress getInetAddress() {
        return inetAddress;
    }

    public int getId() {
        return id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
