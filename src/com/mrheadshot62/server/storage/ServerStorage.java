package com.mrheadshot62.server.storage;

import com.mrheadshot62.api.types.UserPacket;
import com.mrheadshot62.server.Client;
import com.mrheadshot62.server.ServerUser;
import com.sun.istack.internal.Nullable;

import java.util.ArrayList;

/**
 * Created by novak on 04.01.2017.
 */
public class ServerStorage {
    public static final String USER_TABLE = "BlueBear.users";
    private Storage storage;
    public ServerStorage() {
        this.storage = new Storage();
    }

    public ArrayList<Client> getClients(){
        return storage.getClients();
    }

    public void addClient(Client client){
        storage.getClients().add(client);
    }

    public Client getClient(int id){
        return storage.getClients().get(id);
    }

    public void removeClient(int id){
        for (Client c:getClients()){
            if (c.getId() == id){
                getClients().remove(c);
                return;
            }
        }
    }


}
