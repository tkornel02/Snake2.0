package Game;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LeaderBoardTest {
    LeaderBoard leaderBoard;
    @Before
    public void setUp() {
        leaderBoard = new LeaderBoard();
    }

    @Test
    public void InitTest() {
        for (int i =0; i<leaderBoard.getCap(); i++)
            Assert.assertEquals(0,leaderBoard.getScore(i));
    }
    @Test
    public void addToScoresTest() {
        leaderBoard.addToScores(5);
        Assert.assertEquals(5,leaderBoard.getScore(0));
    }

    @Test
    public void getScore() {
        for (int i =0; i<leaderBoard.getCap(); i++)
            leaderBoard.addToScores(i);
        for (int i = 0; i<leaderBoard.getCap(); i++)
            Assert.assertEquals(leaderBoard.getCap()-1-i,leaderBoard.getScore(i));
    }
}