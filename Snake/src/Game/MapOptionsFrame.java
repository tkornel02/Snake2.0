package Game;

import javax.swing.*;
import java.awt.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Frame for displaying options for the SnakePanel
 */
public class MapOptionsFrame extends JFrame {
    /**
     * JTextfield where the width can be written
     */
    static JTextField widthField;
    /**
     * JTextfield where the height can be written
     */
    static JTextField heightField;
    /**
     * Panel for the elements located north
     */
    JPanel upper = new JPanel();
    /**
     * Button to set scw (ScreenWidth)
     */
    JButton setW;
    /**
     * Panel for the elements located south
     */
    JPanel downer = new JPanel();
    /**
     * Button to set sch (ScreenHeight)
     */
    JButton setH;
    /**
     * This will be passed as ScreenWidth when initalizing a SnakeFrame
     */
    static int scw = 600;
    /**
     * This will be passed as ScreenHeight when initalizing a SnakeFrame
     */
    static int sch = 600;

    /**
     * Formats Frame, initializes and adds buttons
     */
    MapOptionsFrame(){

        this.setLayout(new GridLayout(2,1));
        this.setVisible(true);

        widthField = new JTextField(""+scw, 10);
        setW = new JButton("Set Width");
        setW.addActionListener(e -> scw =  Integer.parseInt(widthField.getText()));

        heightField = new JTextField(""+sch, 10);
        setH = new JButton("Set Height");
        setH.addActionListener(e -> sch =  Integer.parseInt(heightField.getText()));

        widthField.addKeyListener(new MenuKeyAdapter());
        heightField.addKeyListener(new MenuKeyAdapter());

        upper.setPreferredSize(new Dimension(200,100));
        upper.add(new JLabel("Map Width"));
        upper.add(widthField);
        upper.add(setW);

        downer.setPreferredSize(new Dimension(200,100));
        downer.add(new JLabel("Map Height"));
        downer.add(heightField);
        downer.add(setH);

        this.add(upper);
        this.add(downer);
        this.pack();
        this.setLocationRelativeTo(null);

    }

    /**
     * returns ScreenWidth
     * @return ScreenWidth
     */
   public static int getScW(){
        return scw;
   }

    /**
     * returns ScreenHeight
     * @return ScreenHeight
     */
   public static int getScH(){
        return sch;
    }

    /**
     * KeyAdapter for textfield, only numbers can be written
     */
    public static class MenuKeyAdapter extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if(!Character.isDigit(c)||(c==KeyEvent.VK_BACK_SPACE||c==KeyEvent.VK_DELETE))
            {
                e.consume();
            }
        }
    }
}
