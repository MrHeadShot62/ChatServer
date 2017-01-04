package com.mrheadshot62.api.types;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by DmitriyRoot on 04.01.2017.
 */
public class CompressendImagePacket extends ImagePacket implements Serializable{

    public CompressendImagePacket(Image image) {
        super(image);
    }
}
