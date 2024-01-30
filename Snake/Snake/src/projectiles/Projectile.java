package projectiles;

import java.io.Serializable;

/**
 * Projectile, which is created when a player invokes the snake.shoot() function
 * Has coordinates and direction
 */
public class Projectile implements Serializable {
    /**
     * X coordinate
     */
    public int x;
    /**
     * Y coordinate
     */
    public int y;
    /**
     * Direction of Projectile
     */
    public char dir;
    /**
     * False if it did not collide with anything
     */
    public boolean hit = false;

    /**
     * sets the attributes
     * @param x x coordinate
     * @param y y coordinate
     * @param dir direction
     */
    public Projectile(int x, int y, char dir){
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    /**
     * Moves the Projectile by ITEM_SIZE distance
     * @param ITEM_SIZE Distance for moving
     */
    public void moveProj(int ITEM_SIZE){
        switch (dir) {
            case 'U' -> y = y - ITEM_SIZE;
            case 'D' -> y = y + ITEM_SIZE;
            case 'L' -> x = x - ITEM_SIZE;
            case 'R' -> x = x + ITEM_SIZE;
        }
    }
}