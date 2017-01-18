package com.mrheadshot62.server.handler;


import com.mrheadshot62.api.types.AuthRegistrationPacket;
import com.mrheadshot62.server.PacketManager;
import com.mrheadshot62.server.storage.ServerStorage;

/**
 * Created by novak on 18.01.2017.
 */
public class RegistrationHandler {
    private AuthRegistrationPacket registerPacket;
    private int id;

    public RegistrationHandler(AuthRegistrationPacket registerPacket, int id) {
        this.registerPacket = registerPacket;
        this.id = id;
        handlePacket(registerPacket);
    }

    protected void handlePacket(AuthRegistrationPacket packet){
        System.out.println("REG "+ packet.getId());
        PacketManager.generateSAuth(true, id);
    }

    public AuthRegistrationPacket getRegisterPacket() {
        return registerPacket;
    }

    public int getId() {
        return id;
    }
}
