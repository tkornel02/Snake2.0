package Game;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Main Menu for the game, the user can start new Snake game,
 * load saved progress, open the best scores, open map size options
 * or exit the game
 */

public class MenuFrame extends JFrame {
    /**
     * Starts a new Snake game when clicked
     */
    MenuButton startButton;
    /**
     * Loads saved game when clicked
     */
    MenuButton loadButton;
    /**
     * Opens new LeaderBoardFrame
     */
    MenuButton scoreListButton;
    /**
     * Opens new MapOptionsFrame
     */
    MenuButton mapButton;
    /**
     * Exits program when clicked
     */
    MenuButton exitButton;
    /**
     * Panel with all the buttons in the Menu
     */
    JPanel menuPanel;

    /**
     * Creates a MenuFrame with Layout, paints background, sets size
     */
    MenuFrame() {
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setFocusable(false);
        this.setVisible(true);
        this.setResizable(false);

        setButtons();
        addListeners();
        setMenuPanel();
        makeFillPanels();
        this.add(menuPanel,BorderLayout.CENTER);

        for (Component c : this.getContentPane().getComponents()){
            c.setBackground((new Color(59, 110, 5)));
        }

        this.setSize(800,500);
        this.setLocationRelativeTo(null);
    }

    /**
     * Creates filler Panels, which helps the Frame look better
     */
    private void makeFillPanels() {
        JPanel fillPanel = new JPanel();
        fillPanel.setPreferredSize(new Dimension(100,500));
        fillPanel.setBackground(new Color(59, 110, 5));
        JPanel fillPanel2 = new JPanel();
        fillPanel2.setPreferredSize(new Dimension(100,500));
        JPanel fillPanel3 = new JPanel();
        fillPanel3.setPreferredSize(new Dimension(500,200));
        this.add(fillPanel,BorderLayout.WEST);
        this.add(fillPanel2,BorderLayout.EAST);
        this.add(fillPanel3,BorderLayout.NORTH);
    }

    /**
     * Initializes menuPanel, and adds the buttons to it
     */
    private void setMenuPanel() {
        menuPanel = new JPanel(new GridLayout(4,1,0,20));
        menuPanel.add(startButton);
        menuPanel.add(loadButton);
        menuPanel.add(mapButton);
        menuPanel.add(scoreListButton);
        menuPanel.add(exitButton);
    }

    /**
     * Initializes the MenuButtons
     */
    private void setButtons() {
        startButton = new MenuButton("Start Game");
        loadButton = new MenuButton("Load Game");
        exitButton = new MenuButton("Exit");
        mapButton = new MenuButton("Map Options");
        scoreListButton = new MenuButton("Scores");
    }

    /**
     * Adds ActionListeners to the MenuButtons
     */
    private void addListeners() {
        startButton.addActionListener(e -> new SnakeFrame(MapOptionsFrame.getScW(),MapOptionsFrame.getScH()));
        exitButton.addActionListener(e->System.exit(0));
        mapButton.addActionListener(e -> new MapOptionsFrame());
        scoreListButton.addActionListener(e-> {
            try {
                new LeaderBoardFrame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        loadButton.addActionListener(e-> {
            try {
                new SnakeFrame(new GameIOHandler().loadGame());
            } catch (IOException | ClassNotFoundException ex) {
                System.err.println("Error while loading Game");
                ex.printStackTrace();
            }
        });
    }
}
