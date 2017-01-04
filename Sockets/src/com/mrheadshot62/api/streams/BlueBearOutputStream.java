package com.mrheadshot62.api.streams;

import com.mrheadshot62.api.MultiPacket;
import com.mrheadshot62.api.Packet;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Created by novak on 04.01.2017.
 */
public class BlueBearOutputStream extends ObjectOutputStream implements BlueBearStream{
    public BlueBearOutputStream(OutputStream out) throws IOException {
        super(out);
    }


    public void writeMultiPacket(MultiPacket p) throws NullPointerException, IOException{
        if (p==null) throw new NullPointerException("Null packet");
        writeObject(p);
        flush();
    }


}
