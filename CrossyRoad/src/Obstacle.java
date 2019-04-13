import javax.swing.*;
import java.awt.*;

public class Obstacle {
    private boolean obstacleLeft;
    private int x[] = new int[5];
    private int y;
    private Image img;

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    private void loadImage(){
        ImageIcon ii = new ImageIcon
                ("src/assets/train.png");
        img = ii.getImage();
    }

    public int[] getX() {
        return x;
    }

    public void setHeadX(int x){
        this.x[0] = x;
        this.x[1]= this.x[0]+10;
        this.x[2]= this.x[0]+20;
        this.x[3]= this.x[0]+30;
        this.x[4]= this.x[0]+40;
    }

    public void setX(int[] x){
        this.x = x;
    }

    public int getY(){
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void moveLeft(){
        for(int i = 0; i < x.length; i++){
            x[i] -= 10;
        }
    }

    public void moveRight(){
        for(int i = 0; i < x.length; i++){
            x[i] += 10;
        }
    }

    public boolean getObstacleDirection(){
        return obstacleLeft;
    }

    public Obstacle(){
        int r = (int) (Math.random() * 10);
        this.x[0] = r * 10;

        for (int i = 1; i < 5; i++) {
            x[i] = x[0] + i*10;
        }

        r = (int) (Math.random() * 29);
        this.y= r * 10;

        int rand = (int) (Math.random() * 10);
        if(rand < 6){
            obstacleLeft = true;
        }
        if(rand > 5){
            obstacleLeft = false;
        }
        loadImage();
    }
}
