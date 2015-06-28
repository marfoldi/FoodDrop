package fooddrop;

import java.io.IOException;

public class FoodDropMain {

    public static void main(String[] args) throws IOException, InterruptedException {
        while(true)
        {
        if(!GameLogic.getInstance().gameOver())  {
            GameFrame.getInstance().startGame();
        }
        else {
            Thread.sleep(3000);
            GameLogic.getInstance().newGame();
        }
        }
    }
}
