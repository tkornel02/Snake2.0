package Game;

import snakes.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * JColorChooser window for changing the Snakes color
 */
public class ColorChanger extends JColorChooser implements ActionListener {
    /**
     * Pops up a JColorCChooser dialog, and sets the Snake color to given color
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Snake.color = JColorChooser.showDialog(this,"COLORS", new Color(1, 211, 3));
    }
}
