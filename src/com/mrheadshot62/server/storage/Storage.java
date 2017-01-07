package com.mrheadshot62.server.storage;

import com.mrheadshot62.server.Client;
import com.mrheadshot62.server.ServerUser;

import java.util.*;

/**
 * Created by novak on 04.01.2017.
 */
class Storage {
//    private ArrayList<Client> clients=new ArrayList<>();
    private HashMap<Integer, Client> clients = new HashMap<>();
    private ArrayList<ServerUser> serverUsers=new ArrayList<>();
    Set<Map.Entry<Integer,Client>> getClients() {
        return clients.entrySet();
    }
    HashMap<Integer, Client> getMap(){
        return clients;
    }
    Client getClient(int id){
        return clients.get(id);
    }

    public ArrayList<ServerUser> getServerUsers() {
        return serverUsers;
    }
}
