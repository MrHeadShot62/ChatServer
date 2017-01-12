package com.mrheadshot62.api.types.answer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Created by DmitriyRoot on 04.01.2017.
 */
public class ServerCompressedImagePacket extends CoreServerAnswerPacket implements Serializable{
    private int width, height;
    private int[] rgb;
    public ServerCompressedImagePacket(Image img) {
        super();
        BufferedImage bim = (BufferedImage)img;
        height = bim.getHeight();
        width = bim.getWidth();
        rgb = new int[width *height];
        bim.getRGB(0,0,width,height,rgb,0,width);
    }

    public Image getImage(){
        BufferedImage bi = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        bi.setRGB(0,0,width,height,rgb,0,width);
        return bi;
    }
}
