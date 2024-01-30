package Game;

import java.io.*;

/**
 * Handler for loading and saving LeaderBoard using Streams
 */
public class LeaderBoardHandler {

    /**
     * Name of the file where the leaderboard is stored
     */
    String fname = "scores.txt";
    /**
     * Read a leaderboard from file and return it
     * @return Loaded LeaderBoard
     * @throws IOException if exception when loading
     * @throws ClassNotFoundException if class is invalid
     */
    public LeaderBoard readLeaderBoard() throws IOException, ClassNotFoundException {
        LeaderBoard lb;
        FileInputStream fis = new FileInputStream(fname);
        ObjectInputStream oos = new ObjectInputStream(fis);
        lb = (LeaderBoard) oos.readObject();
        fis.close();
        return lb;
    }

    /**
     * Saves the parameter leaderboard to file
     * @param lb LeaderBOard to be saved
     * @throws IOException if exception when saving
     */
    public void saveLeaderBoard(LeaderBoard lb) throws IOException {
        FileOutputStream fOut = new FileOutputStream(fname);
        ObjectOutputStream objOut = new ObjectOutputStream(fOut);
        objOut.writeObject(lb);
    }
}
