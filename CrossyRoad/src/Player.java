import javax.swing.*;
import java.awt.*;

public class Player {
    private int player_x;
    private int player_y;
    private Image img;

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }
    private void loadImage(){
        ImageIcon ii = new ImageIcon
                ("src/assets/duck.png");
        img = ii.getImage();
    }

    public int getPlayer_x() {
        return player_x;
    }

    public int getPlayer_y() {
        return player_y;
    }

    public void setPlayer_x(int player_x) {
        this.player_x = player_x;
    }

    public void setPlayer_y(int player_y) {
        this.player_y = player_y;
    }

    public Player(){
        this.player_x = 50;
        this.player_y = 290;

        loadImage();
    }
}