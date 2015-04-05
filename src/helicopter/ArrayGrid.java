package helicopter;

import java.util.ArrayList;

/**
 * A class that represents a 2D grid of objects of type T.
 *
 * @param <T> the type of objects in the array
 */
@SuppressWarnings("unchecked")
public class ArrayGrid<T> {

    /**
     * The number of rows in the array.
     */
    private int numRows;

    /**
     * The number of columns in the array.
     */
    private int numCols;

    /**
     * The elements in the grid.
     */
    private T[][] elements;

    protected ArrayList<Coordinates> changed;

    public class Coordinates {
        public final int row;
        public final int col;

        public Coordinates(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    /**
     * Initializes a grid of type T objects, sized according to the
     * specified numRows and numCols.
     *
     * @param numRows the number of rows the grid should have
     * @param numCols the number of columns the grid should have
     */
    public ArrayGrid(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;

        // ugly
        elements = (T[][]) new Object[numRows][numCols];

        changed = new ArrayList<>();
    }

    /**
     * Places an item of type T in the grid at the specified
     * row and column.
     *
     * @param item the item to place at the location
     */
    public void setCell(T item) {
        Sprite sprite = (Sprite) item;
        elements[sprite.getRow()][sprite.getColumn()] = item;
        changed.add(new Coordinates(sprite.getRow(), sprite.getColumn()));
    }

    public void clearCell(int row, int col) {
        elements[row][col] = (T) new EmptySpace(row, col);
        changed.add(new Coordinates(row, col));
    }

    public void clearChanged() {
        changed.clear();
    }

    /**
     * Gets the item at row row and column col in the grid.
     *
     * @param row the row of the item to get
     * @param col the column of the item to get
     * @return the object of type T at the specified coordinates
     */
    public T getCell(int row, int col) {
        return elements[row][col];
    }

    /**
     * Gets the number of rows in the grid.
     *
     * @return the number of rows in the grid
     */
    public int getNumRows() {
        return numRows;
    }

    /**
     * Gets the number of columns in the grid.
     *
     * @return the number of columns in the grid
     */

    public int getNumCols() {
        return numCols;
    }

    /**
     * Convert sprites in this grid to a string representation.
     *
     * @return a string representing the current state of the grid
     */
    @Override
    public String toString() {
        String maze = "";

        for (int row = 0; row < numRows; row++) {
            for (int column = 0; column < numCols; column++) {
                maze += getCell(row, column);
            }
            maze += "\n";
        }
        return maze;
    }
}