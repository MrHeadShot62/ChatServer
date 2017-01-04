package com.mrheadshot62.api.iLogic;

import com.mrheadshot62.api.types.ImagePacket;
import com.mrheadshot62.api.types.UserPacket;

/**
 * Created by MrHeadShot62 on 04.01.2017.
 */
public interface IDataBase {
    ImagePacket getImage(int id);
    ImagePacket getCompressedImage(ImagePacket image);
    UserPacket getUser(int id);
    void addUser(UserPacket user);
    boolean containedUser(UserPacket user);
    boolean containedImage(ImagePacket image);
    void addImage(ImagePacket image);
    boolean isConnected();

}