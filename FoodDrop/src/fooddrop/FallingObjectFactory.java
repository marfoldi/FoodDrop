package fooddrop;

import java.util.Random;

public class FallingObjectFactory {
    
    private static final Random rnd = new Random();

    public FallingObject create(String type) {
        switch (type) {
            case "Food":
                return new Food((int) (Math.random() * (GameFrame.WIDTH - 20)),
                        (int) (Math.random() * -GameFrame.HEIGHT), rnd.nextBoolean(), (int) (Math.random() * 4 + 1));
            case "Garbage":
                return new Garbage((int) (Math.random() * (GameFrame.WIDTH - 20)),
                        (int) (Math.random() * -GameFrame.HEIGHT));
        }
        return null;
    }
}
