package com.mrheadshot62.server.storage;

import com.mrheadshot62.api.types.UserDatas;
import com.mrheadshot62.server.Client;

import java.util.*;

/**
 * Created by novak on 04.01.2017.
 */
class Storage {
    private HashMap<String, Client> clients = new HashMap<>();
    private HashMap<Integer, UserDatas> users =new HashMap<>();
    Set<Map.Entry<String,Client>> getClients() {
        return clients.entrySet();
    }
    HashMap<String, Client> getMap(){
        return clients;
    }
    HashMap<Integer, UserDatas> getUsers() {
        return users;
    }
}
