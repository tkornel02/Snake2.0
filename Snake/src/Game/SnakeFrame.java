package Game;



import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

/**
 * Frame with SidePanel and SnakePanel
 */
public class SnakeFrame extends JFrame implements Serializable {
    /**
     * SnakePanel of the SnakeFrame, this is the playable part
     */
    private SnakePanel sp;

    /**
     * Initializes the SnakePanel of SnakeFrame with 2 integers and creates SnakeFrame
     * @param scW Screen Width of SnakePanel
     * @param scH Screen Height of SnakePanel
     */
    SnakeFrame(int scW, int scH){
        sp = new SnakePanel(scW,scH);
        createFrame();
    }

    /**
     * Initializes the SnakePanel of SnakeFrame with the parameter SnakePanel and creates SnakeFrame
     * @param snakePanel SnakePanel
     */
    SnakeFrame(SnakePanel snakePanel){
        this.sp = new SnakePanel(snakePanel);
        createFrame();
    }

    /**
     * Formats the Frame, adds new SidePanel and the initialized SnakePanel
     */
    private void createFrame(){
        this.setLayout(new BorderLayout());
        SidePanel tp = new SidePanel(this);
        tp.setPreferredSize(new Dimension(200,500));
        this.add(tp,BorderLayout.WEST);
        this.add(sp, BorderLayout.CENTER);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    /**
     * returns SnakePanel of the SnakeFrame
     * @return SnakePanel of SnakeFrame
     */
    public SnakePanel getSp() {
        return sp;
    }

    /**
     * Saves Progress of SnakePanel and Disposes the Frame
     */
    public void saveSp() {
        this.getSp().saveGame();
        this.dispose();
    }
}
