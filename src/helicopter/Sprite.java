package helicopter;

/**
 * An abstract class to represent all the sprites used in the game.
 */
@SuppressWarnings("unused")
public abstract class Sprite {

    /** The sprite's symbol. */
    final char symbol;

    /** The row location. */
    int row;

    /** The column location. */
    int column;

    /**
     * Initializes a sprite with the specified symbol and value
     * at the coordinates (row, col).
     * @param symbol the symbol for the sprite
     * @param row the row coordinate of the sprite
     * @param col the column coordinate of the sprite
     */
    Sprite(char symbol, int row, int col) {
        this.symbol = symbol;
        this.row = row;
        this.column = col;
    }

    /**
     * Gets the sprite's row coordinate.
     * @return row coordinate
     */
    public abstract int getRow();

    /**
     * Gets the sprite's column coordinate.
     * @return column coordinate
     */
    public abstract int getColumn();

    /**
     * Gets the sprite's symbol.
     * @return the sprite's symbol
     */
    public abstract char getSymbol();

    /**
     * Returns a string representation of the sprite.
     * @return a string representation of the sprite
     */
    @Override
    public abstract String toString();
}