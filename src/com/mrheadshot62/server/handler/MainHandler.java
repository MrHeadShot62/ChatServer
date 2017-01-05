package com.mrheadshot62.server.handler;

import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.Types;
import com.mrheadshot62.api.types.*;

/**
 * Created by novak on 05.01.2017.
 */
public class MainHandler {
    private Packet[] packets;
    private PermissionPacket permisson;



    public MainHandler(Packet[] packets) {
        this.packets = packets;
        if (havePermissionPacket()){
            if (checkSession()) {
                for (Packet p : packets) {
                    switch (p.getType()){
                        case Types.AUTH:
                            onReceivedAuthPacket((AuthPacket)p.getData());
                            break;
                        case Types.Command:
                            onReceivedCommandPacket((CommandPacket)p.getData());
                            break;
                        case Types.Image:
                            onReceivedImagePacket((ImagePacket)p.getData());
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    public void onReceivedAuthPacket(AuthPacket p){
        new AuthHandler(p);
    }
    public void onReceivedCommandPacket(CommandPacket p) {
        new CommandHandler(p);
    }
    public void onReceivedImagePacket(ImagePacket p){
        new ImageHandler(p);
    }
    public void onReceivedUserPacket(UserPacket p){
        new UserHandler(p);
    }

    private boolean checkSession(){
        return true;    //TODO Session
    }

    private boolean havePermissionPacket(){
        for (Packet p:packets){
            if (p.getType() == Types.PERMISSION){
                this.permisson = (PermissionPacket)p.getData();
                return true;
            }
        }
        return false;
    }
}
