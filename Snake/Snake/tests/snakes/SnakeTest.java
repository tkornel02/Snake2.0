package snakes;

import fruits.AmmoFruit;
import fruits.Apple;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import projectiles.Projectile;

import java.util.ArrayList;


public class SnakeTest {
    Snake snake;
    ArrayList<Projectile> projectiles;
    Apple apple;
    AmmoFruit ammoFruit;
    @Before
    public void setUp() {
        snake = new Snake(100);
        snake.x[0] = 0;
        snake.y[0] = 0;
        projectiles = new ArrayList<>();
    }

    @Test
    public void moveDirectionsTest() {
        int item_size = 20;
        snake.dir = 'R';
        snake.move(item_size);
        Assert.assertEquals(item_size,snake.x[0]);
        Assert.assertEquals(0,snake.y[0]);
        snake.dir = 'D';
        snake.move(item_size);
        Assert.assertEquals(item_size,snake.x[0]);
        Assert.assertEquals(item_size,snake.y[0]);
        snake.dir = 'L';
        snake.move(item_size);
        Assert.assertEquals(0,snake.x[0]);
        Assert.assertEquals(item_size,snake.y[0]);
        snake.dir = 'U';
        snake.move(item_size);
        Assert.assertEquals(0,snake.x[0]);
        Assert.assertEquals(0,snake.y[0]);


    }

    @Test
    public void shootNoAmmoTest() {
        snake.ammo = 0;
        snake.shoot(20,projectiles);
        Assert.assertEquals(0,projectiles.size());
    }
    @Test
    public void shootTest(){
        snake.ammo = 3;
        snake.shoot(20,projectiles);
        Assert.assertEquals(1,projectiles.size());
        Assert.assertEquals(2,snake.ammo);
    }
    @Test
    public void appleEatenTest(){
        snake.score = 0;
        apple = new Apple(0,0);
        apple.eatenBy(snake);
        Assert.assertEquals(1,snake.score);
    }
    @Test
    public void ammoFruitEatenTest(){
        ammoFruit = new AmmoFruit(20,20);
        snake.ammo = 0;
        ammoFruit.eatenBy(snake);
        Assert.assertEquals(3,snake.ammo);
    }

}