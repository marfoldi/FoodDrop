package fooddrop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomTexture {

    private static Random randomGenerator;
    private static List<String> foodTexture;
    private static List<String> garbageTexture;
    

    public RandomTexture() {
        foodTexture = new ArrayList<>();
        garbageTexture = new ArrayList<>();
        LoadAll();
        randomGenerator = new Random();
    }

    public static String randomFoodTexture() {
        int index = randomGenerator.nextInt(foodTexture.size());
        String texture = foodTexture.get(index);
        return texture;
    }
    
    public static String randomGarbageTexture() {
        int index = randomGenerator.nextInt(garbageTexture.size());
        String texture = garbageTexture.get(index);
        return texture;
    }
    
    private static void LoadAll() {
        foodTexture.add("/resources/hotdog.gif");
        foodTexture.add("/resources/fries.png");
        foodTexture.add("/resources/tacco.png");
        garbageTexture.add("/resources/cd.gif");
        garbageTexture.add("/resources/floppy.png");
        garbageTexture.add("/resources/radio.png");
    }
}