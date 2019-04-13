import javax.swing.*;
import java.awt.*;

public class NearBackground {
    private Image img;
    private int x;
    private int y;

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    private void loadNearImage(){
        ImageIcon ii = new ImageIcon
                ("src/assets/nearbackground.png");
        img = ii.getImage();

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public NearBackground(){
        this.x = 110;
        this.y = 0;
        loadNearImage();
    }
}






