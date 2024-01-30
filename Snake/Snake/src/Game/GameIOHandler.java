package Game;

import java.io.*;

/**
 * Handles Saving and Loading of a SnakePanel Object
 */
public class GameIOHandler {

    /**
     * Sets name of file where the SnakePanel will be saved
     * @param fname new filename
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * Name of the file where the saved game is stored
     */
    private String fname = "saved_game.txt";

    /**
     * Loads Snakepanel and returns it
     * @return SnakePanel loaded from file
     * @throws IOException if error while reading
     * @throws ClassNotFoundException if class is invalid
     */
    public SnakePanel loadGame() throws IOException, ClassNotFoundException {
        SnakePanel sp;
        FileInputStream fis = new FileInputStream(fname);
        ObjectInputStream oos = new ObjectInputStream(fis);
        sp = (SnakePanel) oos.readObject();
        fis.close();
        return sp;
    }

    /**
     * Save the given SnakePanel
     * @param sp SnakePanel to be saved
     * @throws IOException if error while writing
     */
    public void saveGame(SnakePanel sp) throws IOException {
        if (!new File(fname).exists()) throw new IOException();
        FileOutputStream fOut = new FileOutputStream(fname);
        ObjectOutputStream objOut = new ObjectOutputStream(fOut);
        objOut.writeObject(sp);
        fOut.close();
    }
}
