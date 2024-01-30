package Game;

import fruits.AmmoFruit;
import fruits.Apple;
import fruits.Fruit;
import projectiles.Projectile;
import snakes.Snake;
import snakes.ZSnake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import static java.awt.event.KeyEvent.*;


/**
 * The Panel where the Game is displayed, and controlled
 */
public class SnakePanel extends JPanel implements ActionListener, Serializable {
    /**
     * SerialVersionUID for class validation
     * */
    @Serial
    private static final long serialVersionUID = 1000L;

    /**
     * returns Width of screen
     * @return Width of screen
     */
    public int getScreenWidth() {
        return SCREEN_WIDTH;
    }

    /**
     * returns Height of screen
     * @return Height of screen
     */
    public int getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    /**
     * Width of SnakePanel
     */
    private final int SCREEN_WIDTH;
    /**
     * Heigth of SnakePanel
     */
    private final int SCREEN_HEIGHT;
    /**
     * Size of Object in Game.SnakePanel
     */
    private static final int ITEM_SIZE = 20;

    /**Speed of Game,
     * higher the number, slower the game */
    private static final int DELAY = 40;
    /**Number of units, calculated from SCREEN_HEIGHT and SCREEN_WIDTH*/
    private static int GAME_UNITS;
    /**
     * Game State true if game is running
     */
    private static boolean running = false;
    /**
     * Controllable Snake Object
     */
    public Snake snake;

    /**
     * Fruit on the Map
     */
    public Fruit fruit = new Apple(0,0);
    /**
     * Helps with of moving speed, and randomization
     */
    public static int counter = -1;
    /**
     * Random Object for randomization
     */
    private final Random random = new Random();
    /**
     * Timer for timimg of game
     */
    private final Timer t = new Timer(DELAY,this);
    /**
     * Maximum of Zombie Snakes
     */
    private static final int Z_COUNT = 3;
    /**
     * Gameover State, true if game is over
     */
    boolean gameOver = false;
    /**
     * Arraylist for storing Projectile Objects, which the snake shoots
     */
    private ArrayList<Projectile> projectiles = new ArrayList<>(GAME_UNITS);
    /**
     * Arraylist for storing the Zombie Snakes in the Panel
     */
    private ArrayList<ZSnake> zsnakes = new ArrayList<>(Z_COUNT);

    /**
     * Panel to display stats of the game
     */
    StatsPanel stp;
    /**
     * LeaderBoard Object which is loaded in from file, and saved to file when game is over
     */
    LeaderBoard leaderBoard;
    {
        try {
            leaderBoard = new LeaderBoardHandler().readLeaderBoard();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Initializes snake, sets dimensions of Panel
     * @param scW ScreenWidth
     * @param scH ScreenHeight
     */
    SnakePanel(int scW,int scH){
        SCREEN_WIDTH = scW;
        SCREEN_HEIGHT = scH;
        GAME_UNITS = (SCREEN_WIDTH/ITEM_SIZE*SCREEN_HEIGHT/ITEM_SIZE);
        snake = new Snake(GAME_UNITS);

        createPanel();
    }

    /**
     * Copies attributes of SnakePanel
     * @param snakePanel SnakePanel to be copied
     */
    SnakePanel(SnakePanel snakePanel){
        SCREEN_WIDTH = snakePanel.getScreenWidth();
        SCREEN_HEIGHT = snakePanel.getScreenHeight();
        GAME_UNITS = (SCREEN_WIDTH/ITEM_SIZE*SCREEN_HEIGHT/ITEM_SIZE);
        snake = snakePanel.snake;
        fruit = snakePanel.fruit;
        zsnakes = snakePanel.zsnakes;
        projectiles = snakePanel.projectiles;
        running = false;
        createPanel();
    }

    /**
     * Formats the Panel, initializes StatsPanel
     */
    private void createPanel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        JPanel labelPanel = new JPanel(new GridLayout(2,1));
        labelPanel.setBackground(new Color(0,0,0,200));
        stp = new StatsPanel(this);
        this.add(stp);
    }
    /**
     * Starts new Game
     */
    public void gameStart(){
        startSnake();
        gameOver = false;
        counter = -1;
        running=true;
        projectiles.clear();
        zsnakes.clear();
        generateFruit();
        t.start();


    }

    /**
     * Saves  the Game
     */
    public void saveGame(){
        if (running)
            pauseGame();
        try {
            new GameIOHandler().saveGame(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Pauses Game
     */
    public void pauseGame(){
        if (!gameOver){
            if (t.isRunning()) t.stop();
            else t.start();
            running = !running;
        }
    }
    /**
     * Starts the snake at starting position (middle of map)
     */
    public void startSnake() {
        snake = new Snake(GAME_UNITS);
        snake.dir = 'R';
        snake.x[0] = (SCREEN_WIDTH/ITEM_SIZE)/2*ITEM_SIZE;
        snake.y[0] = (SCREEN_HEIGHT/ITEM_SIZE)/2*ITEM_SIZE;

        for (int i=1; i < snake.bodyParts; i++){
            snake.x[i] = snake.x[i-1]-ITEM_SIZE;
            snake.y[i] = snake.y[i-1];
        }
    }


    /**
     * Generates Zombie Snake, there are less ZSnakes on the map than Z_COUNT.
     * The direction the ZSnake comes from depends on the canDo variable
     */
    public void generateZSnake(){
        if(zsnakes.size()<Z_COUNT){
            int[] sides = new int[4];
            sides[0] = SCREEN_HEIGHT;
            sides[1] = 0;
            sides[2] = SCREEN_WIDTH;
            sides[3] = 0;
            if (counter %2==0){
                zsnakes.add(new ZSnake(
                        random.nextInt(0,SCREEN_WIDTH/ITEM_SIZE)*SCREEN_WIDTH/(SCREEN_WIDTH/ITEM_SIZE),
                    sides[random.nextInt(0,2)],
                    'U'));
            }
            else{
                zsnakes.add(new ZSnake(sides[random.nextInt(2,4)],
                        random.nextInt(0,SCREEN_HEIGHT/ITEM_SIZE)*SCREEN_HEIGHT/(SCREEN_HEIGHT/ITEM_SIZE),
                        'R'));
            }
        }
    }

    /**
     * Randomly generates a Fruit in the grid, Fruit type depends on canDo variable
     */
    public void generateFruit(){
        fruit.x = random.nextInt(SCREEN_WIDTH/ITEM_SIZE)*ITEM_SIZE;
        fruit.y = random.nextInt(SCREEN_HEIGHT/ITEM_SIZE)*ITEM_SIZE;

        for (int i=snake.bodyParts; i>=0; i--){
            if (snake.x[i]== fruit.x && snake.y[i] == fruit.y){
                generateFruit();
                System.out.println("Fruit generated on Snake");
            }
        }
        if (counter %2 == 0 ||  counter %5 ==0){
            fruit = new AmmoFruit(fruit.x, fruit.y);
        }
        else {
            fruit = new Apple(fruit.x, fruit.y);
        }

    }

    /**
     * Checks Collision of Snake with itself,
     * Snake-Fruit Collision,
     * removes Zombie Snake if it is hit and
     * removes projectile if out of bounds
     */
    public void checkCollision(){
        //Snake Collision (Game Over)
        if (snake.x[0]>=SCREEN_WIDTH || snake.x[0]<0) {gameOver();}
        else if (snake.y[0]>=SCREEN_HEIGHT  || snake.y[0]<0) {gameOver();}
        for (int i = 1; i< snake.bodyParts; i++){
            if (snake.x[0] == snake.x[i] &&
                    snake.y[0] == snake.y[i]){
                gameOver();

            }
        }
        //Snake Fruit collision
        if(snake.x[0]==fruit.x && snake.y[0]==fruit.y) {fruit.eatenBy(snake); generateFruit();
            //System.out.println("Fruit: "+fruit.x+" "+fruit.y);
            }

        //Projectile collision with ZSnake or Out of Bounds Projectile
        projectiles.removeIf(p -> (p.x > SCREEN_WIDTH || p.x < 0 || p.y < 0 || p.y > SCREEN_HEIGHT) || p.hit);
        zsnakes.removeIf(this::checkProjectileHit);
    }

    /**
     * Checks if a Zombie Snake and the Snake collides
     */
    public void checkSnakeZSnakeCollision(){
        for (ZSnake zs : zsnakes){
            for (int i=0; i < snake.bodyParts; i++){
                if(zs.zX[0] == snake.x[i] && zs.zY[0] == snake.y[i]){
                    if(i==0){
                        gameOver();
                        //System.out.println("x: " + zs.zX[0] + " == " +snake.x[i] + " y: " + zs.zY[0] + " == " +snake.y[i]);
                    }
                    else {
                        snake.score = snake.score - (snake.bodyParts-i);
                        snake.bodyParts = i;
                    }
                }
            }
        }
    }

    /**
     * @param zsnake ZSnake to be checked
     * Checks if ZSnake has been hit with Projectile
     * @return true if ZSnake is hit
     */
    boolean checkProjectileHit(ZSnake zsnake){
            for (int j = 0; j < ZSnake.Z_LENGTH; j++){
                for(Projectile p : projectiles){
                    if(zsnake.zX[j] == p.x && zsnake.zY[j]==p.y){
                        p.hit = true;
                        snake.score+=2;
                        return true;
                    }
                }
            }
        return false;
    }

    /**
     * Stops the Game, saves the score
     */
    public void gameOver() {
        running = false;
        gameOver = true;

        leaderBoard.addToScores(snake.score);
        try {
            new LeaderBoardHandler().saveLeaderBoard(leaderBoard);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        t.stop();
    }

    /**
     * Paints objects in Panel
     */
    @Override
    public void paintComponent(Graphics g){
            super.paintComponent(g);
            draw(g);

    }

    /**
     * draws all the Objects in the Panel (Grid, Snakes, Fruits, texts)
     * @param g Graphics Object
     */
    public void draw(Graphics g) {

            g.setColor(new Color(100, 100, 100, 50));
            for (int i = 0; i <= SCREEN_WIDTH / ITEM_SIZE; i++) {
                g.drawLine(i * ITEM_SIZE, 0, i * ITEM_SIZE, SCREEN_HEIGHT);
            }
            for (int i = 0; i <= SCREEN_HEIGHT / ITEM_SIZE; i++) {
                g.drawLine(0, i * ITEM_SIZE, SCREEN_WIDTH, i * ITEM_SIZE);
            }

            g.setColor(Color.green);
            for (int i = 0; i<snake.bodyParts;i++) {
                g.setColor(Snake.color);
                g.fillRect(snake.x[i], snake.y[i], ITEM_SIZE, ITEM_SIZE);
            }
            g.setColor(Color.red);
            for (Projectile projectile : projectiles) {
                g.fillRect(projectile.x, projectile.y, ITEM_SIZE, ITEM_SIZE);
            }

            g.setColor(fruit.c);
            g.fillOval(fruit.x,fruit.y,ITEM_SIZE,ITEM_SIZE);

            g.setColor(new Color(100, 15, 173));
            for (ZSnake zsnake : zsnakes) {
                for (int j = 0; j < ZSnake.Z_LENGTH; j++){
                    g.fillRect(zsnake.zX[j], zsnake.zY[j], ITEM_SIZE, ITEM_SIZE);
                }
            }

        if (counter ==-1 && !gameOver){
            String startGameStr = "Press Pause to Continue!";
            g.setColor(Color.WHITE);
            g.setFont(new Font("Times New Roman",Font.BOLD, 30));
            g.drawString(startGameStr,getWidth()/2-g.getFontMetrics().stringWidth(startGameStr)/2,getHeight()/2);

        }
        if(gameOver){
            String endGameStr = "Final Score: "+snake.score;
            g.setColor(Color.RED);
            g.setFont(new Font("Times New Roman",Font.BOLD, 30));
            g.drawString(endGameStr,getWidth()/2-g.getFontMetrics().stringWidth(endGameStr)/2,getHeight()/2);
        }
    }

    /**
     * With help of Timer and counter moves and draws objects
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(running){
            if (counter %2==0){
                snake.move(ITEM_SIZE);
                //System.out.println("x: " +snake.x[0] + " y: "  +snake.y[0]);
            }
            if (counter %4==0){
                for (ZSnake z : zsnakes){
                    z.moveZ(snake,random, ITEM_SIZE);
                    //System.out.println("zX: " +z.zX[0] + " zY: "  +z.zY[0]);
                }
            }
            for (Projectile p : projectiles){
                p.moveProj(ITEM_SIZE);
            }
            if (counter %75==0){generateZSnake();}
            counter++;
            checkCollision();
            checkSnakeZSnakeCollision();
            repaint();
        }

        stp.setScoreText("Score: "+ snake.score);
        stp.setAmmoText("Ammo: "+ snake.ammo);


    }


    /**
     * KeyAdapter for Control
     */
    public class MyKeyAdapter extends KeyAdapter implements Serializable{
        @Override
        public void keyPressed(KeyEvent e){
            switch(e.getKeyCode()){
                case VK_W: if (snake.dir != 'D') snake.dir = 'U'; break;
                case VK_S: if (snake.dir != 'U') snake.dir = 'D'; break;
                case VK_D: if (snake.dir != 'L') snake.dir = 'R'; break;
                case VK_A: if (snake.dir != 'R') snake.dir = 'L'; break;
                case VK_SPACE: if (running)  snake.shoot(ITEM_SIZE,projectiles); break;
            }
        }
    }
}
