package fruits;

import snakes.Snake;

import java.awt.*;

/**
 * Fruit which gives the Snake ammo if they collide
 */
public class AmmoFruit extends Fruit{

    /**
     * Sets coordinates and color
     * @param x this integer will be assigned as x coordinate
     * @param y this integer will be assigned as y coordinate
     */
    public AmmoFruit(int x, int y){
        super(x,y);
        c = Color.BLUE;
    }
    /**
     * adds ammo for the Snake given as parameter
     * @param snake a Snake that eats the AmmoFruit
     */
    @Override
    public void eatenBy(Snake snake) {
        int reward = 3;
        snake.ammo+= reward;
    }
}
