package com.mrheadshot62.api.types;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Created by novak on 04.01.2017.
 */
public class ImagePacket extends CorePacket implements Serializable{
    private int width, height;
    private int[] rgb;
    private String name;

    public ImagePacket(Image img, String name) {
        super();
        this.name = name;
        BufferedImage bim = (BufferedImage)img;
        height = bim.getHeight();
        width = bim.getWidth();
        rgb = new int[width *height];
        bim.getRGB(0,0,width,height,rgb,0,width);
    }

    public String getName() {
        return name;
    }

    public Image getImage(){
        BufferedImage bi = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        bi.setRGB(0,0,width,height,rgb,0,width);
        return bi;
    }
}
