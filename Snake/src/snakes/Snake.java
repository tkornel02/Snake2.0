package snakes;

import projectiles.Projectile;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Stores coordinates, bodyparts, score, ammo, color and direction of Snake
 */
public class Snake implements Serializable {
    /**
     * Color of the Snake
     */
    public static Color color = new Color(1, 211, 3);
    /**
     * x coordinate of Snake
     */
    public int[] x;
    /**
     * y coordinate of Snake
     */
    public int[] y;
    /**
     * Number of bodyparts of the Snake
     */
    public int bodyParts = 6;
    /**
     * Number of ammo
     */
    public int ammo = 3;
    /**
     * Score of snake
     */
    public int score = 0;
    /**
     * Current direction of Snake
     */
    public char dir = 'R';

    /**
     * Creates arrays for x and y coordinates with the size GAME_UNITS
     * @param GAME_UNITS Number of possible fields on map
     */
    public Snake(int GAME_UNITS){
        x = new int[GAME_UNITS];
        y = new int[GAME_UNITS];
    }

    /**
     * Moves head of the Snake, and all the other bodyparts
     * @param ITEM_SIZE  Size of Items on map, movement distance
     */
    public void move(int ITEM_SIZE) {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        switch (dir) {
            case 'U' -> y[0] = y[0] - ITEM_SIZE;
            case 'D' -> y[0] = y[0] + ITEM_SIZE;
            case 'L' -> x[0] = x[0] - ITEM_SIZE;
            case 'R' -> x[0] = x[0] + ITEM_SIZE;

        }
        //System.out.println("x= "+x[0]+" y= "+y[0]);
    }

    /**
     * Creates new Projectile depending on the direction and ammo of the Snake
     * @param ITEM_SIZE Size of Items on map, movement distance
     * @param projectiles Projectiles List, the new projectile will be added here
     */
    public void shoot(int ITEM_SIZE, ArrayList<Projectile> projectiles) {
        if (ammo > 0) {
            Projectile p = new Projectile(0, 0, 'R');
            switch (dir) {
                case 'U' -> {
                    p.y = y[0] - ITEM_SIZE;
                    p.x = x[0];
                    p.dir = 'U';
                }
                case 'D' -> {
                    p.y = y[0] + ITEM_SIZE;
                    p.x = x[0];
                    p.dir = 'D';
                }
                case 'L' -> {
                    p.x = x[0] - ITEM_SIZE;
                    p.y = y[0];
                    p.dir = 'L';
                }
                case 'R' -> {
                    p.x = x[0] + ITEM_SIZE;
                    p.y = y[0];
                    p.dir = 'R';
                }
            }

            projectiles.add(p);
            ammo--;
        }
    }

}
