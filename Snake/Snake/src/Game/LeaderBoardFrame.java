package Game;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Frame for displaying LeaderBoards
 */
public class LeaderBoardFrame extends JFrame {

    /**
     * formats frame, reads LeaderBoard from file and creates labels
     * @throws IOException if exception while loading
     */
    public LeaderBoardFrame() throws IOException {
         this.setLayout(new GridLayout(11,1));

         this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
         this.setFocusable(false);
         this.setVisible(true);
         this.setResizable(false);
         this.add(new JLabel("Best scores",SwingConstants.CENTER));

        LeaderBoard leaderBoard = null;
        try {
            leaderBoard = new LeaderBoardHandler().readLeaderBoard();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        for (int i = 0; i< leaderBoard.getCap(); i++){
             this.add(new JLabel(""+ leaderBoard.getScore(i),SwingConstants.CENTER));
         }
        this.pack();
         setLocationRelativeTo(null);
     }


}
