package projectiles;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProjectileTest {
    Projectile projectile;
    @Before
    public void setUp(){
        projectile = new Projectile(0,0, 'R');
    }

    @Test
    public void moveProjTest() {
        projectile.moveProj(25);
        Assert.assertEquals(25,projectile.x);
        Assert.assertEquals(0,projectile.y);
    }

}