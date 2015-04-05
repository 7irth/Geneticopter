package helicopter;

/**
 * A class that represents the wall sprite.
 */
public class Wall extends Sprite {

    /**
     * Initializes a wall with the specified symbol and value
     * at the coordinates (row, col).
     * @param row the row coordinate of the wall
     * @param col the column coordinate of the wall
     */
    public Wall(int row, int col) {
        super(GUI.WALL, row, col);
    }

    /**
     * Gets the wall's row coordinate.
     * @return row coordinate
     */
    @Override
    public int getRow() {
        return row;
    }

    /**
     * Gets the wall's column coordinate.
     * @return column coordinate
     */
    @Override
    public int getColumn() {
        return column;
    }

    /**
     * Gets the wall's symbol.
     * @return the wall's symbol
     */
    @Override
    public char getSymbol() {
        return symbol;
    }

    /**
     * Returns a string representation of the wall.
     * @return a string representation of the wall.
     */
    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}