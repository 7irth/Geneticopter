package helicopter;

import java.util.HashSet;
import java.util.Random;

public class HelicopterGame {

    private ArrayGrid<Sprite> cave;
    private Copter copter;
    private HashSet<Obstacle> obstacles;
    private HashSet<Obstacle> initObstacles;

    private int xSize;
    private int ySize;
    private int ceiling;
    private int floor;
    private int numberOfObstacles;

    private Random rando;

    private boolean crashed = false;
    private boolean gasLastTime = false;
    private boolean created = false;

    public HelicopterGame() {
        this.xSize = Play.X_SIZE;
        this.ySize = Play.Y_SIZE;
        numberOfObstacles = Play.OBSTACLES;

        rando = new Random();

        ceiling = xSize / 6;  // ceiling & floor start at 1/6th of cave size
        floor = xSize - ceiling - 1;

        initializeCave();
    }

    public void initializeCave() {
        cave = new ArrayGrid<>(xSize, ySize);

        for (int row = 0; row < xSize; row++)
            for (int col = 0; col < ySize; col++)
                if (row < ceiling || row > floor)
                    cave.setCell(new Wall(row, col));
                else
                    cave.setCell(new EmptySpace(row, col));

        copter = new Copter(xSize / 2, 42);  // copter starting position
        cave.setCell(copter);
        crashed = false;

        initializeObstacles();
    }

    public void initializeObstacles() {

        if (!created) {
            obstacles = new HashSet<>();

            for (int i = 0; i < numberOfObstacles; i++) {
                Obstacle o = new Obstacle(
                        rando.nextInt(floor - ceiling) + ceiling,
                        rando.nextInt(ySize));

                obstacles.add(o);
                cave.setCell(o);
            }
            initObstacles = new HashSet<>(obstacles);
            created = true;
        } else obstacles = initObstacles;


    }

    public void moveObstacles() throws CollisionException {
        for (Obstacle o : obstacles) {
            cave.clearCell(o.getRow(), o.getColumn());

            if (o.getColumn() == 0) o.transport(o.getRow(), ySize - 1);
            else o.moveAlong();

            if (cave.getCell(o.getRow(), o.getColumn()) instanceof Copter)
                throw new CollisionException();
            else cave.setCell(o);
        }
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

    public void gas(boolean applied) throws CollisionException {
        cave.clearCell(copter.getRow(), copter.getColumn());

        // copter up when gas applied, down if not applied twice
        if (applied) {
            copter.fly();
            gasLastTime = true;
        } else {
            if (!gasLastTime) copter.fall();
            gasLastTime = false;
        }

        if (cave.getCell(copter.getRow(), copter.getColumn()) instanceof Wall
                || cave.getCell(copter.getRow(), copter.getColumn()) instanceof Obstacle) {
            throw new CollisionException();
        } else cave.setCell(copter);
    }

    public class CollisionException extends Exception {
        public CollisionException() {
        }
    }
}
