package helicopter;

public class HelicopterGame {

    private ArrayGrid<Sprite> cave;
    private Copter copter;
    private int xSize;
    private int ySize;
    private int ceiling;
    private int floor;
    private boolean crashed = false;

    public HelicopterGame(int numRows, int numCols) {
        this.xSize = numRows;
        this.ySize = numCols;

        ceiling = xSize / 6;  // ceiling & floor start at 1/6th of cave size
        floor = xSize - ceiling - 1;

        cave = new ArrayGrid<>(numRows, numCols);

        initializeCave();
    }

    public void initializeCave() {
        for (int row = 0; row < xSize; row++)
            for (int col = 0; col < ySize; col++)
                if (row < ceiling || row > floor)
                    cave.setCell(new Wall('|', row, col));
                else
                    cave.setCell(new Wall(' ', row, col));

        copter = new Copter('*', xSize / 2, 42);
        cave.setCell(copter);
        crashed = false;
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
        return xSize;
    }

    /**
     * Gets the number of columns in the cave.
     *
     * @return the number of columns in the cave
     */
    public int getNumCols() {
        return ySize;
    }

    public boolean isCrashed() {
        return crashed;
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

    public void applyingGas(boolean applied) throws CollisionException {
        cave.clearCell(copter.getRow(), copter.getColumn());

        if (applied) copter.fly();
        else copter.fall();

        if (copter.getRow() < ceiling || (copter.getRow() > floor)) {
            throw new CollisionException();
        } else cave.setCell(copter);
    }

    public class CollisionException extends Exception {
        public CollisionException() {
        }
    }
}
