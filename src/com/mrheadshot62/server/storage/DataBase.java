package com.mrheadshot62.server.storage;

import com.mrheadshot62.server.Client;
import com.mrheadshot62.server.ServerUser;

import java.util.ArrayList;

/**
 * Created by novak on 04.01.2017.
 */
class DataBase {
    private ArrayList<Client> clients;
    private ArrayList<ServerUser> serverUsers;
    ArrayList<Client> getClients() {
        return clients;
    }

    public ArrayList<ServerUser> getServerUsers() {
        return serverUsers;
    }
}
