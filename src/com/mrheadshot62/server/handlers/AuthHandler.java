package com.mrheadshot62.server.handlers;

import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.types.AuthType;
import com.mrheadshot62.api.types.AuthorisationPacket;
import com.mrheadshot62.api.types.CorePacket;
import com.mrheadshot62.server.Client;
import com.mrheadshot62.server.PacketManager;
import com.mrheadshot62.server.storage.ServerStorage;

/**
 * Created by novak on 19.01.2017.
 */
public class AuthHandler extends Handler {


    public AuthHandler(int type) {
        super(type);
    }

    @Override
    public boolean handlePacket(Packet p) {
        if (verifyPacket(p)){
            AuthorisationPacket authorisationPacket = (AuthorisationPacket)p.getData();
            System.out.println("AUTH PACKET: id #"+p.getId());
            for (Object o:authorisationPacket.getData()){
                System.out.println("AUTH PACKET: "+o.toString());
            }
            if (authorisationPacket.getType() == AuthType.GOOGLE){
                int id = ServerStorage.getInstance().addGoogleUser((String)(authorisationPacket.getData()[0]));
                if (ServerStorage.getInstance().checkEmail((String)(authorisationPacket.getData()[0]))){
                    PacketManager.generateCheckSecretKey(true, clientId);
                }else{
                    PacketManager.generateCheckSecretKey(false, clientId);
                }
            }
            return true;
        }else{
            return false;
        }
    }

    public void setClient(Client client){

    }
}
