package com.mrheadshot62.server.handler;

import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.types.ImagePacket;
import com.mrheadshot62.server.handler.abstracts.AImageHandler;
import com.mrheadshot62.server.storage.SQLBuilder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by novak on 05.01.2017.
 */
class ImageHandler extends AImageHandler{

    public ImageHandler(ImagePacket p, int id) {
        super(p, id);
    }

    @Override
    protected void handleImagePacket(ImagePacket imagePacket) {
        System.out.println("[DEBUG] ImagePacket received:");
        System.out.println("[DEBUG]           Image name: "+imagePacket.getImage());
//        SQLBuilder.getInstance().addInsertQuery()
        try {
            File f = new File(String.format("img/%s.jpg", imagePacket.getName()));
            System.out.println(imagePacket.getName());
            ImageIO.write((BufferedImage)imagePacket.getImage(), "jpg", f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
