import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import static java.awt.event.KeyEvent.*;

public class Game extends JPanel implements ActionListener {

    private final int WIDTH = 110;
    private final int HEIGHT = 300;
    private final int SQUARE_SIZE = 10;
    private final int ALL_SQUARES = 3300;

    private boolean inGame = true;
    private int DELAY = 100;
    private int dots;
    private int level = 1;

    private Image img;
    private Player player;
    private NearBackground nearBackground;
    private Timer timer;

    public List<Obstacle> obstacles = new ArrayList<>();
    public List<Integer> placeObs = new ArrayList<>();

    public Game() {
        initBoard();
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    private void loadImage() {
        ImageIcon ii = new ImageIcon
                ("src/assets/background.png");
        img = ii.getImage();
    }

    private void loadGameOverImage() {
        ImageIcon ii = new ImageIcon
                ("src/assets/gameover.png");
        img = ii.getImage();

    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setDoubleBuffered(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        initGame();
    }

    private void initGame() {
        player = new Player();
        nearBackground = new NearBackground();
        obstacles.add(new Obstacle());

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void createNewObstacle() {

        for(Obstacle ob : obstacles){
            placeObs.add(ob.getY());
        }

        int r = (int) (Math.random() * 29 );
        Obstacle temp = new Obstacle();
        temp.setY(r * 10);
        obstacles.add(temp);
    }

    private void checkCollision(){
        for(int i = 0; i < obstacles.size(); i++){
            for(int j = 0 ; j < obstacles.get(i).getX().length; j++){
                if (player.getPlayer_x() == obstacles.get(i).getX()[j] && player.getPlayer_y() == obstacles.get(i).getY()){
                    inGame = false;
                }
            }
        }
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if(inGame) {
            g2d.drawImage(getImg(), 0, 0, this);
            g2d.drawImage(player.getImg(), player.getPlayer_x(), player.getPlayer_y(), this);

            for (int i = 0; i < obstacles.size(); i++) {
                for(int j = 0; j < obstacles.get(i).getX().length; j++){
                    g2d.drawImage(obstacles.get(i).getImg(), obstacles.get(i).getX()[j], obstacles.get(i).getY(), this);
                }
            }
            showLevel(g);
            g2d.drawImage(nearBackground.getImg(), nearBackground.getX(), nearBackground.getY(), this);
        }else{
            g2d.drawImage(getImg(), 0, 0, this);
        }
    }

    private void obstacleMove () {
        for(Obstacle ob : obstacles) {

            if (ob.getObstacleDirection() == true) {
                ob.moveLeft();
            }
            if (ob.getObstacleDirection() == false) {
                ob.moveRight();
            }
            if (ob.getX()[0] == -80) {
                ob.setHeadX(WIDTH);
            }
            if (ob.getX()[0] == WIDTH+30) {
                ob.setHeadX(-50);
            }
        }
    }

    private void showLevel(Graphics g){
        Font text = new Font ("Arial", Font.BOLD, 10);
        FontMetrics metr = getFontMetrics(text);

        g.setColor(Color.white);
        g.setFont(text);
        g.drawString("LEVEL:" + level, (WIDTH - metr.stringWidth("LEVEL:")) - WIDTH + 35, HEIGHT - HEIGHT + 8);
    }

    private void level(){
        if (player.getPlayer_y() == 0) {
            level++;
            this.player = null;
            this.player = new Player();
            obstacles.clear();

            for (int i = 0; i < level; i++) {
                createNewObstacle();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkCollision();
            obstacleMove();
            level();
            loadImage();
        }else{
            loadGameOverImage();
        }
        repaint();
    }

    private class TAdapter extends KeyAdapter{
        public void keyPressed (KeyEvent e){

            int key = e.getKeyCode();

            if(key == VK_UP && player.getPlayer_y() > 0){
                player.setPlayer_y(player.getPlayer_y() - SQUARE_SIZE);
            }
            if(key == VK_DOWN && player.getPlayer_y() < HEIGHT - 10){
                player.setPlayer_y(player.getPlayer_y() + SQUARE_SIZE);
            }
            if(key == VK_LEFT && player.getPlayer_x() > 0){
                player.setPlayer_x(player.getPlayer_x() - SQUARE_SIZE);
            }
            if(key == VK_RIGHT && player.getPlayer_x() < WIDTH - 10){
                player.setPlayer_x(player.getPlayer_x() + SQUARE_SIZE);
            }
        }
    }
}