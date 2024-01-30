package Game;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class GameIOHandlerTest {
    GameIOHandler handler;
    @Before
    public void setUp() {
        handler = new GameIOHandler();
    }

    @Test (expected = IOException.class)
    public void loadGameIOException() throws IOException, ClassNotFoundException {
        handler.setFname("not_exist.txt");
        handler.loadGame();
    }

    @Test (expected = IOException.class)
    public void saveGameIOException() throws IOException {
        handler.setFname("not_me.txt");
        handler.saveGame(new SnakePanel(300,300));
    }
}