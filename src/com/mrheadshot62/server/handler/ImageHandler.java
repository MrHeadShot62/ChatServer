package com.mrheadshot62.server.handler;

import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.types.ImagePacket;
import com.mrheadshot62.server.handler.abstracts.AImageHandler;

/**
 * Created by novak on 05.01.2017.
 */
class ImageHandler extends AImageHandler{

    public ImageHandler(ImagePacket p, int id) {
        super(p, id);
    }

    @Override
    protected void handleImagePacket(ImagePacket imagePacket) {
        System.out.println("ImagePacket received");
    }
}
