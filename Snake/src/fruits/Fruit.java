package fruits;

import snakes.Snake;

import java.awt.*;
import java.io.Serializable;
import java.util.Random;
/**
 * Stores coordinates, colors of Fruit and has a method for behavior between Snake and Fruit.
 * */
public abstract class Fruit implements Serializable {
    /**
     * sets Fruit coordinates
     * @param x this parameter will be the x coordinate
     * @param y this parameter will be the y coordinate
     *
     */
    public Fruit(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * x coordinate of Fruit
     */
    public int x;
    /**
     * y coordinate of Fruit
     */
    public int y;
    /**
     * Color of Fruit
     */
    public Color c;
    /**
     * Fruit-Snake interaction
     * @param snake snake which ate the Fruit
     */
    abstract public void eatenBy(Snake snake);



}
