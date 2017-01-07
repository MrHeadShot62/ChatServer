package com.mrheadshot62.api.types.answer;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by DmitriyRoot on 04.01.2017.
 */
public class ServerCompressedImagePacket extends CoreServerAnswerPacket implements Serializable{

    private String image64;

    public ServerCompressedImagePacket(String image) {
        this.image64 = image;
    }
}
