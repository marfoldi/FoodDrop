package fooddrop;

public class Food extends FallingObject {

    private final boolean special;
    private final int value;
    private static int size;

    public Food(int x, int y, boolean special, int value) {
        super(x, y);
        this.special = special;
        this.value = value;
        setImage();
    }
    
    private void setImage() {
        img = imgflw.selectImage((RandomTexture.randomFoodTexture())).getImage();
    }

    public boolean isSpecial() {
        return special;
    }

    public int getValue() {
        return value;
    }
}
