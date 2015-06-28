package fooddrop;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

public abstract class FallingObject extends JComponent {

    protected int x, y;
    private static int spd = 5;
    protected ImageIcon img;
    protected ImageFlyWeightFactory imgflw;
    protected RandomTexture rndtexture;

    public FallingObject(int x, int y) {
        this.x = x;
        this.y = y;
        rndtexture = new RandomTexture();
        imgflw = GameLogic.getImageFlyWeightFactory();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        img.paintIcon(null, g, x, y);
    }

    public void move() {
        y += spd;
        repaint();
    }

    public static void setSpd(int spd) {
        FallingObject.spd = spd;
    }
    
    public double getXPosition() {
        return x;
    }

    public double getYPosition() {
        return y;
    }
}
