package helicopter;

public class HelicopterGame {

    private ArrayGrid<Sprite> cave;
    private Copter copter;

    public HelicopterGame() {
        cave = new ArrayGrid<>(30, 300);
        copter = new Copter('*', 12, 120);
        initializeCave();
    }

    private void initializeCave() {
        for (int row = 0; row < cave.getNumRows(); row++)
            for (int col = 0; col < cave.getNumCols(); col++)
                if (row <= 4 || row >= 25)
                    cave.setCell(new Wall('|', row, col));
                else
                    cave.setCell(new Wall(' ', row, col));

        cave.setCell(copter);
    }

    public ArrayGrid<Sprite> getCave() {
        return cave;
    }

    /**
     * Gets the number of rows in the cave.
     *
     * @return the number of rows in the cave
     */
    public int getNumRows() {
        return cave.getNumRows();
    }

    /**
     * Gets the number of columns in the cave.
     *
     * @return the number of columns in the cave
     */
    public int getNumCols() {
        return cave.getNumCols();
    }

    /**
     * Gets the sprite object located at the specified row and column.
     *
     * @param row the sprite's row coord
     * @param col the sprite's column coord
     * @return the sprite object
     */
    public Sprite get(int row, int col) {
        return cave.getCell(row, col);
    }
}
