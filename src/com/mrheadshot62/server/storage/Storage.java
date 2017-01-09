package com.mrheadshot62.server.storage;

import com.mrheadshot62.server.Client;
import com.mrheadshot62.server.User;

import java.util.*;

/**
 * Created by novak on 04.01.2017.
 */
class Storage {
//    private ArrayList<Client> clients=new ArrayList<>();
    private HashMap<Integer, Client> clients = new HashMap<>();
    private HashMap<Integer, User> users =new HashMap<>();
    Set<Map.Entry<Integer,Client>> getClients() {
        return clients.entrySet();
    }
    HashMap<Integer, Client> getMap(){
        return clients;
    }

    HashMap<Integer, User> getUsers() {
        return users;
    }
}
