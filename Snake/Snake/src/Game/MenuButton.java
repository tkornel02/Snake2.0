package Game;

import javax.swing.*;
import java.awt.*;

/**
 * Formatted Button for the MenuFrame
 */
public class MenuButton extends JButton {

    MenuButton(String text){
        super(text);
        setBackground(new Color(255, 255, 255, 255));
        setFocusable(false);
    }
}
