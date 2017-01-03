package com.mrheadshot62.api.streams;

import com.mrheadshot62.api.types.Packet;
import com.sun.istack.internal.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

/**
 * Created by novak on 04.01.2017.
 */
public class BlueBearInputStream extends ObjectInputStream implements BlueBearStream{
    public BlueBearInputStream(InputStream in) throws IOException {
        super(in);
    }

    @Nullable
    public Packet readPacket() throws IOException{
        Object o;
        try {
            o = readObject();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
        if (o instanceof Packet) {
            return (Packet) o;
        } else {
            return null;
        }
    }
}
