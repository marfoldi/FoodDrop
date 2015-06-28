package fooddrop;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class Doge extends JComponent implements KeyListener {

    private static int left = 0, right = 0;
    private static int score = 0;
    private static int highestScore = 0;
    private static int life = 3;
    private static ImageIcon img;
    private static int x;
    private static int height;
    private static int width;
    private static Doge instance; //SUCH INSTANCE

    private Doge() {
        x = 250;
        height = 150;
        width = 150;
        setImage();
        highestScore = getHighestScore();
    }

    public static Doge getInstance() {
        return instance == null ? new Doge() : instance;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        img.paintIcon(null, g2, x, 400);
        Font font = new Font("Times New Roman", Font.BOLD, 18);
        g2.setFont(font);
        g2.setColor(Color.GREEN);
        g2.drawString("Score: " + score, 20, 50);
        g2.setColor(Color.RED);
        g2.drawString("Life: " + life, 20, 68);
    }

    private void setImage() {
        img = new ImageIcon(this.getClass().getResource("/resources/doge.gif"));
        Image temp = img.getImage();
        Image newimg = temp.getScaledInstance(height, width, java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(newimg);
    }

    private void setHighestScore() {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("src/resources/highestScore.txt"));
            writer.write(Integer.toString(highestScore));
        } catch (IOException e) {
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
            }
        }
    }
    
    public int getHighestScore() {
        int hs = 0;
        Scanner scanner;
        try {
            scanner = new Scanner(new File("src/resources/highestScore.txt"));
            hs = scanner.nextInt();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Doge.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hs;
    }
    
    public void addPoint() {
        ++score;
    }

    public void move() {
        if (left == 1) {
            if (x > 0) {
                x -= 4;
            }
        }
        if (right == 1) {
            if (x + this.getWidth() < GameFrame.WIDTH) {
                x += 4;
            }
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                left = 1;
                break;
            case KeyEvent.VK_RIGHT:
                right = 1;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                left = 0;
                break;
            case KeyEvent.VK_RIGHT:
                right = 0;
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }

    public void decreaseLife() {
        life--;
        repaint();
    }

    public void increaseScore(int value) {
        score += value;
        if(score>highestScore) {
            highestScore = score;
            setHighestScore();
        }
        repaint();
    }

    public void setDefault() {
        life = 3;
        left = 0;
        right = 0;
        score = 0;
    }

    public static int getLife() {
        return life;
    }

    public int getXPos() {
        return x;
    }

    public int getYPos() {
        return 400;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }
}
