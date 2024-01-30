package Game;

import javax.swing.*;
import java.awt.*;

/**
 * Button for SidePanel
 */
public class PButton extends JButton {

    /**
     * Creates the style of the button
     * @param text text of the button
     */
    PButton(String text){
        super(text);
        this.setFont(new Font("Times New Roman",Font.BOLD,17));
        this.setBackground(new Color(1, 211, 3, 255));
        this.setFocusable(false);
    }
}
