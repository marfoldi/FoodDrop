package fooddrop;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class GameLogic {

    private FallingObjectFactory factory;
    private List<FallingObject> fallingObjects;
    private Doge catcher;
    private static ImageFlyWeightFactory imgflwf;
    private static GameLogic instance;

    private GameLogic() {
        factory = new FallingObjectFactory();
        fallingObjects = new ArrayList<>();
        catcher = Doge.getInstance();
        imgflwf = new ImageFlyWeightFactory();
    }
    
    public static GameLogic getInstance() {
        return instance == null ? new GameLogic() : instance;
    }

    public void makeObject() {
        String[] types = {"Food", "Garbage"};
        int idx = new Random().nextInt(types.length);
        String random = (types[idx]);
        fallingObjects.add(factory.create(random));
    }

    public void moveObjects() {
        for (Iterator<FallingObject> it = fallingObjects.iterator(); it.hasNext();) {
            FallingObject o = it.next();
            if (o != null && o instanceof FallingObject) {
                o.move();
            }
            if (o.getYPosition() > 610) {
                it.remove();
            }
        }
    }

    public void paintObjects(Graphics g) {
        for (FallingObject o : fallingObjects) {
            o.paintComponent(g);
        }
    }

    public List<FallingObject> getFallingObjects() {
        return fallingObjects;
    }

    public Doge getCatcher() {
        return catcher;
    }

    public boolean gameOver() {
        return 0 >= Doge.getLife();
    }
    
    public void newGame() {
        catcher.setDefault();
    }

    public static ImageFlyWeightFactory getImageFlyWeightFactory() {
        return imgflwf;
    }
}
