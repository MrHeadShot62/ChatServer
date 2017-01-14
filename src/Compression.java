import java.awt.*;
import java.io.*;
import java.util.*;
import java.awt.image.*;

import javax.imageio.*;
import javax.imageio.stream.ImageOutputStream;

class Compression {
    private String path = "D:\\test\\";
    public static void main(String[] args) throws IOException {
        new Compression().compress(null, null);
    }

    public void compress(Image image, String name){
        FileOutputStream fos=null;
        BufferedImage img=null;
        File input = new File(path+"img.jpg");
//        BufferedImage img = (BufferedImage)image;
        try {
            img = ImageIO.read(input);
            File f = new File(path+"comp3.jpg");
            fos = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        Iterator<ImageWriter>writers =  ImageIO.getImageWritersByFormatName("jpg");
        ImageWriter writer = writers.next();
        try {
            ImageOutputStream ios = ImageIO.createImageOutputStream(fos);
            writer.setOutput(ios);

            ImageWriteParam param = writer.getDefaultWriteParam();

            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(0.1f);
            writer.write(null, new IIOImage(img, null, null), param);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}