package com.mrheadshot62.server.storage;

import com.mrheadshot62.server.Client;

import java.util.ArrayList;

/**
 * Created by novak on 04.01.2017.
 */
public class ServerStorage {
    private DataBase dataBase;
    public ServerStorage() {
        this.dataBase = new DataBase();
    }

    public ArrayList<Client> getClients(){
        return dataBase.getClients();
    }

    public void addClient(Client client){
        dataBase.getClients().add(client);
    }

    public Client getClient(int id){
        return dataBase.getClients().get(id);
    }
}
