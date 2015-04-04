package helicopter;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    private HelicopterGame game;
    private JLabel[][] tile;
    private int rows;
    private int cols;

    public GUI(HelicopterGame game) {
        this.game = game;
        rows = game.getNumRows();
        cols = game.getNumCols();
    }

    public HelicopterGame getGame() {
        return game;
    }

    public void launchGame() {
        setTitle("Geneticopter");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);

        Container c = this.getContentPane();
        c.setLayout(new GridLayout(rows, cols));
        tile = new JLabel[rows][cols];

        GUIListener listener = new GUIListener(this);
        this.addKeyListener(listener);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                tile[i][j] = new JLabel(game.get(i, j).toString());
                tile[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                tile[i][j].setVerticalAlignment(SwingConstants.CENTER);
                c.add(tile[i][j]);
            }
        }

        pack();
        setVisible(true);
        setResizable(false);
    }

    public void update() {
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                tile[i][j].setText(game.get(i, j).toString());
    }
}
