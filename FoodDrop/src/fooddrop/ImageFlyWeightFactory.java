package fooddrop;

import java.util.ArrayList;
import java.util.List;

public class ImageFlyWeightFactory {
    private static List<ImageFlyWeight> pool;
    
    public ImageFlyWeightFactory() {
        pool = new ArrayList<>();
    }
    
    public ImageFlyWeight selectImage(String imgsrc) {
        for(ImageFlyWeight imgflw : pool) {
            if(imgflw.getPath().equals(imgsrc)) {
                return imgflw;
            }
        }
        ImageFlyWeight imgflw = new ImageFlyWeight(imgsrc);
        pool.add(imgflw);
        return imgflw;
    }
    
    public static void clearPool() {
        pool.clear();
    }
}