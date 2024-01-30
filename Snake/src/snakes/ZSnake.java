package snakes;

import java.io.Serializable;
import java.util.Random;

/**
 * Zombie Snake, which can move on its own towards a Snake, has a fixed length
 */
public class ZSnake implements Serializable {
    /**
     * Length of ZSnake
     */
    public static int Z_LENGTH = 6;
    /**
     * Array for storing the X coordinates
     */
    public int[] zX = new int[Z_LENGTH];
    /**
     * Array for storing the Y coordinates
     */
    public int[] zY = new int [Z_LENGTH];
    /**
     * Current direction of ZSnake
     */
    public char dir;

    /**
     * Initializes the X and Y arrays
     * @param x First X coordinate
     * @param y First Y coordinate
     * @param d direction of Zsnake
     */
    public ZSnake(int x, int y, char d){
        zX[0] = x;
        zY[0] = y;
        dir = d;
        for (int i=1;i<Z_LENGTH;i++){
            zX[i]= zX[i-1];
            zY[i]= zY[i-1];

        }
    }

    /**
     * Calculates move towards the Snake Objects head, but prefers moving horizontally
     * @param snake Snake to be followed
     * @return Next Direction
     */
    public char calcMoveX(Snake snake){
        if (snake.x[0] > zX[0]) return 'R';
        else if(snake.x[0] == zX[0]) {
             if (snake.y[0] < zY[0]) return 'U';
             if (snake.y[0] > zY[0]) return 'D';
        }
        return 'L';
    }
    /**
     * Calculates move towards the Snake Objects head, but prefers moving vertically
     * @param snake Snake to be followed
     * @return Next Direction
     */
    public char calcMoveY(Snake snake){
        if (snake.y[0] < zY[0]) return 'U';
        else if (snake.y[0] == zY[0]) {
             if (snake.x[0] > zX[0]) return 'R';
             if (snake.x[0] < zX[0]) return 'L';
        }
        return 'D';
    }


    /**
     * Moves the ZSnake towards the parameter Snake, chooses randomly from calcMoveX and calcMoveY
     * @param snake Snake to be followed
     * @param random Random object for randomizing the moves
     * @param ITEM_SIZE Moving distance
     */
    public void moveZ(Snake snake, Random random, int ITEM_SIZE) {
        for (int i = Z_LENGTH-1; i > 0; i--) {
            zX[i] = zX[i - 1];
            zY[i] = zY[i - 1];
        }

        char nxtX = calcMoveX(snake);
        char nxtY = calcMoveY(snake);
        char [] nxtDirs = new char[3];
        nxtDirs[0] = nxtX;
        nxtDirs[1] = nxtX;
        nxtDirs[2] = nxtY;
        char nxtDir = nxtDirs[random.nextInt(0,3)];
        switch (nxtDir) {
            case 'U' -> zY[0] = zY[0] - ITEM_SIZE;
            case 'D' -> zY[0] = zY[0] + ITEM_SIZE;
            case 'L' -> zX[0] = zX[0] - ITEM_SIZE;
            case 'R' -> zX[0] = zX[0] + ITEM_SIZE;
        }


    }

}