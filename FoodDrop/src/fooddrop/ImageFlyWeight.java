package fooddrop;

import java.awt.Image;
import javax.swing.ImageIcon;

public class ImageFlyWeight {
    private ImageIcon img;
    private final String path;
    private static int size = 50;
    
    public ImageFlyWeight(String imgsrc)
    {
        img = new ImageIcon(getClass().getResource(imgsrc));
        path = imgsrc;
        Image temp = img.getImage();
        Image newimg = temp.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(newimg);
    }
    
    public ImageIcon getImage() {
        return img;
    }
    
    public String getPath() {
        return path;
    }

    public int getSize() {
        return size;
    }
    
    public static void setSize(int size) {
        ImageFlyWeight.size = size;
    }
}
