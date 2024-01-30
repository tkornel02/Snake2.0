package Game;

import javax.swing.*;
import java.awt.*;

/**
 * The SidePanel in SnakeFrame, next to SnakePanel, used for starting new game, saving or pausing, or changing Snake color
 */
public class SidePanel extends JPanel {
    /**
     * Starts new game if clicked
     */
    PButton startButton;
    /**
     * Saves and Exits the Parent SnakeFrame if clicked
     */
    PButton exitButton;
    /**
     * Pauses SnakePanel if clicked
     */
    PButton pauseButton;
    /**
     * Opens a ColorChanger frame if clicked
     */
    PButton changeColorButton;
    /**
     * Parent SnakeFrame, used for closing and saving
     */
    SnakeFrame sFrame;

    /**
     * Formats SidePanel, sets PButtons, and adds them to the Frame
     * @param sf Parent SnakeFrame
     */
    SidePanel(SnakeFrame sf){
        this.setLayout(new GridLayout(4,1,0,30));
        sFrame = sf;
        this.setBackground(new Color(0, 136, 2, 255));
        this.setFocusable(false);
        setButtons();
        this.add(startButton);
        this.add(pauseButton);
        this.add(changeColorButton);
        this.add(exitButton);

    }

    /**
     * Initializes Buttons and sets ActionListeners
     */
    private void setButtons() {
        startButton = new PButton("Start Game");
        startButton.addActionListener(e -> sFrame.getSp().gameStart());
        pauseButton = new PButton("Pause Game");
        pauseButton.addActionListener(e -> sFrame.getSp().pauseGame());
        exitButton = new PButton("Save & Exit Game");
        exitButton.addActionListener(e -> sFrame.saveSp());
        changeColorButton = new PButton("Change Snake Color");
        changeColorButton.addActionListener(new ColorChanger());
    }


}
