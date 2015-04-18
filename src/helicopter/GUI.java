package helicopter;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    // internal game constants
    private static final int DELAY = 100;
    public static final char COPTER = '^';
    public static final char WALL = '|';
    public static final char EMPTY = ' ';
    public static final char OBSTACLE = 'o';

    private final HelicopterGame game;
    private JLabel[][] tile;
    private final int rows;
    private final int cols;
    private int distance;

    public GUI(HelicopterGame game) {
        this.game = game;
        rows = game.getNumRows();
        cols = game.getNumCols();
        distance = 0;
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

    public void update(Boolean buttonPressed) throws HelicopterGame.CollisionException {
        game.moveObstacles();

        if (Play.GUI) {
            if (!buttonPressed) try {
                Thread.sleep(GUI.DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // redraw changed tiles
            for (ArrayGrid.Coordinates c : game.getCave().getChanged())
                tile[c.row][c.col].setText(game.get(c.row, c.col).toString());

            // draw score
            int scoreCol = 10;
            for (char c : Integer.toString(distance).toCharArray())
                tile[rows - 1][cols - scoreCol--].setText(String.valueOf(c));

            game.getCave().clearChanged();
        }
    }

    public void updateDistance(boolean reset) {
        if (reset) distance = 0;
        else distance++;
    }
}
