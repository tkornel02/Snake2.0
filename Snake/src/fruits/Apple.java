package fruits;

import snakes.Snake;

import java.awt.*;

/**
 * Gives a Snake a point if they collide
 */
public class Apple extends Fruit{
    /**
     * Sets coordinates and color
     * @param x this integer will be assigned as x coordinate
     * @param y this integer will be assigned as y coordinate
     */
    public Apple(int x, int y){
        super(x,y);
        c = Color.RED;
    }

    /**
     * Grows by 1 and adds 1 point to the score of the Snake given as parameter
     * @param snake a Snake that eats the Apple
     */
    @Override
    public void eatenBy(Snake snake){
        snake.bodyParts++; snake.score++;
    }
}
