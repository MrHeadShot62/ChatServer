package com.mrheadshot62.server;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by novak on 09.01.2017.
 */
public class ImageCompressor {
    public void compress(String source, String dis) {
        try {
            FileInputStream in = new FileInputStream(source);
            FileOutputStream out = new FileOutputStream(dis);
            int next = 0;
            int count = in.read();
            while ((next = in.read()) >= 0) {
                int counter = 1;
                if (count == next) {
                    counter++;
                    while (next == (count = in.read())) {
                        counter++;
                    }
                    while (counter >= 63) {     // ???
                        out.write(255);      // ???
                        out.write(next);
                        //System.out.println((0xc0 + 63) + " " + count);
                        counter -= 63;
                    }
                    if (counter > 1) {
                        out.write(0xc0 + counter);      // ???
                        out.write(next);
                        //System.out.println((0xc0 + counter) + " " + counter);
                    }
                } else {
                    if (count <= 0xc0) {      // ???
                        //System.out.println(count);
                        out.write(count);
                        count = next;
                    } else {
                        out.write(0xc1);      // ???
                        out.write(count);
                        count = next;
                        //System.out.println((0xc1) + count);
                    }
                }
            }
            if (count <= 0xc0) {      // ???
                //System.out.println(count);
                out.write(count);
            } else {
                out.write(0xc1);
                out.write(count);
                //System.out.println((0xc1) + count);
            }
            in.close();
            out.close();
        } catch (IOException e) {
        }
    }

    public void decompress(String source, String dis) {
        try {
            FileInputStream in = new FileInputStream(source);
            FileOutputStream out = new FileOutputStream(dis);
            int count = 0;
            while ((count = in.read()) >= 0) {
                if (count == 0xc1) {
                    out.write(in.read());
                } else if (count <= 0xc0) {
                    out.write(count);
                } else if (count > 0xc1) {
                    int next = in.read();
                    for (int i = 0; i < (count - 0xc0); i++) {
                        out.write(next);
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}
