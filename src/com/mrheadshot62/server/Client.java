package com.mrheadshot62.server;

import com.mrheadshot62.api.streams.BlueBearInputStream;
import com.mrheadshot62.api.streams.BlueBearOutputStream;

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

    public Client(Socket socket){
        try {
            this.input = new BlueBearInputStream(socket.getInputStream());
            this.output = new BlueBearOutputStream(socket.getOutputStream());
            this.inetAddress = socket.getInetAddress();
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
}
