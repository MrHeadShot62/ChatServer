package com.mrheadshot62.api.types;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by novak on 04.01.2017.
 */
public class ImagePacket implements Serializable{
    private String image64;

    public ImagePacket(Image image) {
        this.image64 = "123"; // TODO SERIALIZE IMAGE
    }

    public String getImage64() {
        return image64;
    }
}
