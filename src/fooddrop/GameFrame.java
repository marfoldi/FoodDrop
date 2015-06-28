package fooddrop;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class GameFrame extends JFrame {

    public static final int HEIGHT = 600;
    public static final int WIDTH = 600;
    private static GameFrame instance;
    private final GameLogic logic;
    private Image offScreen;
    private static ImageIcon img;
    
    public static GameFrame getInstance() {
        return instance == null ? new GameFrame() : instance;
    }

    private GameFrame() {
        logic = GameLogic.getInstance();
        addKeyListener(logic.getCatcher());
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setTitle("FoodDrop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        timer.start();
    }

    @Override
    public void paint(Graphics main) {
        if (offScreen == null) {
            offScreen = createImage(WIDTH, HEIGHT);
            img = new ImageIcon(this.getClass().getResource("/resources/bg.jpg"));
            Image temp = img.getImage();
            Image newimg = temp.getScaledInstance(HEIGHT, WIDTH, java.awt.Image.SCALE_SMOOTH);
            img = new ImageIcon(newimg);
        }
        Graphics g = offScreen.getGraphics();
        img.paintIcon(null, g, 0, 0);
        logic.paintObjects(g);
        logic.getCatcher().paint(g);

        main.drawImage(offScreen, 0, 0, null);

        if (logic.gameOver()) {
            Graphics2D g2 = (Graphics2D) main;
            g2.setColor(Color.BLACK);
            Font font = new Font("Times New Roman", Font.BOLD, 40);
            g2.setFont(font);
            g2.drawString("Game Over! Score: " + logic.getCatcher().getScore(),
                    120, 300);
            
            if (logic.getCatcher().getScore()==logic.getCatcher().getHighestScore()) {
                g2.setColor(Color.GREEN);
                g2.drawString("New High score!", 150, 350);
            }
            else {
                g2.setColor(Color.BLUE);
                g2.drawString("Highest Score: " + logic.getCatcher().getHighestScore(),
                    150, 350);
            }
            try {
                Thread.sleep(3000);
                setVisible(false);
                ImageFlyWeightFactory.clearPool();
                ImageFlyWeight.setSize(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
    }
    
    private Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            logic.getCatcher().addPoint();
        }
    });
    
    public synchronized void startGame() throws IOException {
        while (!logic.gameOver()) {
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
            }
            logic.getCatcher().move();

            if (logic.getFallingObjects().size() < 10) {
                logic.makeObject();
            }
            logic.moveObjects();
            for (Iterator<FallingObject> it = logic.getFallingObjects().iterator(); it.hasNext();) {
                FallingObject o = it.next();
                {
                    if (o.getXPosition() + 20 > logic.getCatcher().getXPos()
                            && o.getXPosition() < logic.getCatcher().getXPos() + logic.getCatcher().getWidth()
                            && o.getYPosition() + 20 > logic.getCatcher().getYPos()
                            && o.getYPosition() < logic.getCatcher().getYPos() + logic.getCatcher().getHeight()) {
                        if (o instanceof Food) {
                            Food f = (Food) o;
                            if(f.isSpecial()) {
                                ImageFlyWeightFactory.clearPool();
                                ImageFlyWeight.setSize((int) (Math.random() * 80 + 20));
                                FallingObject.setSpd((int) (Math.random() * 4 + 3));
                            }
                            logic.getCatcher().increaseScore(f.getValue());
                        } else {
                            logic.getCatcher().decreaseLife();
                        }
                        try {
                            it.remove();
                        }
                        catch(ConcurrentModificationException ex){}
                    }
                }
                repaint();
            }
        }
    }
}
