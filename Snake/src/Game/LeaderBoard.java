package Game;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * LeaderBoard for storing scores in reverse order
 */
public class LeaderBoard implements Serializable{
    /**
     * SerialVersionUID for class validation
     * */
    @Serial
    private static final long serialVersionUID = -7997730868826226595L;
    /**
     * Capacity of LeaderBoard
     */
    private final int cap = 10;
    /**
     * List for storing integers
     */
    public List<Integer> scores = new ArrayList<>(cap);

    /**
     * Adds a new score to the list, if it is a top 10 score
     * @param newScore new score to be added
     */
    public void addToScores(int newScore){

        if (scores.size()==cap && newScore > scores.get(scores.size()-1)){
            scores.remove(scores.size()-1);
        }
        scores.add(newScore);
        scores.sort(Collections.reverseOrder());
    }

    /**
     * return capacity
     * @return maximum number of scores stored
     */
    public int getCap() {
        return cap;
    }

    /**
     * return a score with the given index
     * @param i index of score
     * @return score with the index of i
     */
    public int getScore(int i) {
        if (i>=scores.size()) return 0;
        return scores.get(i);
    }

}
