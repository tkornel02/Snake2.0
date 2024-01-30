package Game;



import javax.swing.*;
import java.awt.*;

/**
 * Panel for displaying Score and Ammo of Snake on SnakePanel
 */
public class StatsPanel extends JPanel {
    /**
     * Label for displaying score
     */
    JLabel scoreLabel;
    /**
     * Label for displaying ammo
     */
    JLabel ammoLabel;
    /**
     * Score to be displayed
     */
    int score;
    /**
     * Ammo to be displayed
     */
    int ammo;

    /**
     * Formats the StatsPanel, adds the JLabels, and sets color transparent
     * @param sp The SnakePanel whose Snakes Score and Ammo will be displayed
     */
    StatsPanel(SnakePanel sp){
        score = sp.snake.score;
        ammo = sp.snake.ammo;
        this.setBackground(Color.white);
        this.setFocusable(false);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setBackground(new Color(0,0,0,0));
        scoreLabel = new JLabel("Score: "+score, JLabel.CENTER);
        ammoLabel = new JLabel("Ammo: "+ammo);
        scoreLabel.setForeground(Color.WHITE);
        ammoLabel.setForeground(Color.WHITE);
        this.add(scoreLabel);
        this.add(ammoLabel);
    }

    /**
     * Changes scoreLabel text
     * @param text the new score to be displayed
     */
    public void setScoreText(String text){
        scoreLabel.setText(text);
    }

    /**
     * Changes ammolabel text
     * @param text the new ammo to be displayed
     */
    public void setAmmoText(String text){
        ammoLabel.setText(text);
    }

}
