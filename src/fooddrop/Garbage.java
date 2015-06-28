package fooddrop;

public class Garbage extends FallingObject {

    public Garbage(int x, int y) {
        super(x, y);
        setImage();
    }

    private void setImage() {
        img = imgflw.selectImage((RandomTexture.randomGarbageTexture())).getImage();
    }
}
