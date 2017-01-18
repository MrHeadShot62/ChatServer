package com.mrheadshot62.server.handler;

import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.PermissionLevel;
import com.mrheadshot62.api.Types;
import com.mrheadshot62.api.types.*;
import com.mrheadshot62.server.Client;

/**
 * Created by novak on 05.01.2017.
 */
public class MainHandler {
    private final Client client;
    private Packet[] packets;
    private PermissionPacket permission;



    public MainHandler(Packet[] packets, Client c) {
        this.client = c;
        this.packets = packets;
        if (havePermissionPacket()){
            if (checkSession()) {
                for (Packet p : packets) {
                    switch (p.getType()){
                        case Types.PERMISSION:
                            System.out.println("Сравниваем права доступа с базой данных");
                            break;
                        case Types.AUTH:
                            if (checkPermissionLevel(this.permission, PermissionLevel.GUEST))
                            onReceivedAuthPacket((AuthPacket)p.getData());
                            break;
                        case Types.Command:
                            if (checkPermissionLevel(this.permission, PermissionLevel.SENDCOMMAND))
                            onReceivedCommandPacket((CommandPacket)p.getData());
                            break;
                        case Types.Image:
                            if (checkPermissionLevel(this.permission, PermissionLevel.SENDIMAGE))
                            onReceivedImagePacket((ImagePacket)p.getData());
                            break;
                        case Types.AUTH_REGISTRATION:
                            if (checkPermissionLevel(this.permission, PermissionLevel.GUEST)){
                                new RegistrationHandler((AuthRegistrationPacket)p.getData(), permission.getId());
                            }
                            break;
                        case Types.AUTH_READY_USER:
                            System.out.println(((AuthReadyUser)p.getData()).getKey());
                            break;
                        case Types.USER:
                            if (checkPermissionLevel(this.permission, PermissionLevel.USER))
                            onReceivedUserPacket((UserPacket) p.getData());
                            break;
                        default:
                            System.out.println("Сервер получил пакет который не может распознать. TypeErrorPacket ["+ p.getType()+"]");
                            break;
                    }
                }
            }
        }
    }

    public void onReceivedAuthPacket(AuthPacket p){
        new AuthHandler(p, permission.getId());
    }
    public void onReceivedCommandPacket(CommandPacket p) {
        new CommandHandler(p, permission.getId());
    }
    public void onReceivedImagePacket(ImagePacket p){
        new ImageHandler(p, permission.getId());
    }
    public void onReceivedUserPacket(UserPacket p){
        new UserHandler(p, permission.getId());
    }

    private boolean checkSession(){
        if(this.permission.getSessionKey().equals("")){
            System.out.println("В пакете полученом от пользователя [] пустая сессия");
           return false;
        }else {
            System.out.println(permission.getSessionKey());
            client.setId(permission.getUserID());
            return true;
        }
    }

    private boolean havePermissionPacket(){
        for (Packet p:packets){
            if (p.getType() == Types.PERMISSION){
                this.permission = (PermissionPacket)p.getData();
                if(this.permission.getPermissionLevel() >= PermissionLevel.GUEST){
                    return true;
                }else {
                    System.out.println("Забаненый пользователь [] посылал пакеты серверу!");
                    return false;
                }
            }
        }
        System.out.println("Пользователь [] прислал пакет без прав доступа");
        return false;
    }

    private boolean checkPermissionLevel(PermissionPacket permissionPacket, int permissionLevel){
        if(permissionPacket.getPermissionLevel() >= permissionLevel){
            return true;
        }else {
            System.out.println("Пользователь [] пытался получить доступ к уровню доступа ["+ permissionLevel +"] . Его уровень доступа ["+ permissionPacket.getPermissionLevel()+"]");
            return false;
        }
    }
}
