package helicopter;

import java.util.HashSet;
import java.util.Random;

@SuppressWarnings("unused")
public class HelicopterGame {

    private ArrayGrid<Sprite> cave;
    private Copter copter;
    private HashSet<Obstacle> obstacles;
    private HashSet<Obstacle> initObstacles;

    private final int rows;
    private final int cols;
    private final int ceiling;
    private final int floor;
    private final int numberOfObstacles;

    private final Random rando;

    private boolean crashed;
    private boolean gasLastTime;
    private boolean created;

    public HelicopterGame() {
        this.rows = Play.ROWS;
        this.cols = Play.COLUMNS;
        numberOfObstacles = Play.OBSTACLES;

        rando = new Random();

        ceiling = rows / 6;  // ceiling & floor start at 1/6th of cave size
        floor = rows - ceiling - 1;

        initializeCave();
    }

    private void initializeCave() {
        cave = new ArrayGrid<>(rows, cols);

        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++)
                if (row < ceiling || row > floor)
                    cave.setCell(new Wall(row, col));
                else
                    cave.setCell(new EmptySpace(row, col));

        copter = new Copter(rows / 2, 42);  // copter starting position
        gasLastTime = false;
        crashed = false;
        cave.setCell(copter);

        initializeObstacles();
    }

    private void initializeObstacles() {
        if (!created) {
            obstacles = new HashSet<>();

            for (int i = 0; i < numberOfObstacles; i++) {
                Obstacle o = new Obstacle(
                        rando.nextInt(floor - ceiling) + ceiling,
                        rando.nextInt(cols));

                obstacles.add(o);
                cave.setCell(o);
            }
            created = true;
            initObstacles = deepObstacleCopy(obstacles);
        } else {
            obstacles = deepObstacleCopy(initObstacles);
        }
    }

    private HashSet<Obstacle> deepObstacleCopy(HashSet<Obstacle> original) {
        HashSet<Obstacle> copied = new HashSet<>();

        //noinspection Convert2streamapi
        for (Obstacle o : original)
            copied.add(Obstacle.getObsInstance(o.getRow(), o.getColumn()));

        return copied;
    }

    public void moveObstacles() throws CollisionException {
        for (Obstacle o : obstacles) {
            cave.clearCell(o.getRow(), o.getColumn());

            if (o.getColumn() == 0) o.transport(o.getRow(), cols - 1);
            else o.moveAlong();

            if (cave.getCell(o.getRow(), o.getColumn()) instanceof Copter)
                throw new CollisionException();
            else cave.setCell(o);
        }
    }

    public void readObstacleLocations(String locations) {
        obstacles = new HashSet<>();

        for (String coords : locations.split(String.valueOf(GUI.OBSTACLE))) {
            String[] coord = coords.split(",");
            Obstacle o = new Obstacle(Integer.parseInt(coord[0]),
                                      Integer.parseInt(coord[1]));

            obstacles.add(o);
            cave.setCell(o);
        }
        initObstacles = deepObstacleCopy(obstacles);
        created = true;

        // deep copying back and forth
        initializeCave();
    }

    public String writeObstacleLocations() {
        String s = "";

        for (Obstacle o : obstacles)
            s += String.format("%d,%do", o.getRow(), o.getColumn());

        return s.substring(0, s.lastIndexOf('o'));
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
        return rows;
    }

    /**
     * Gets the number of columns in the cave.
     *
     * @return the number of columns in the cave
     */
    public int getNumCols() {
        return cols;
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

        // copter up 1 when gas applied, down 2 if not applied twice in a row
        if (applied) {
            copter.fly();
            gasLastTime = true;
        } else {
            if (!gasLastTime) copter.fall();
            gasLastTime = false;
        }

        if (cave.getCell(copter.getRow(), copter.getColumn()) instanceof Wall
                || cave.getCell(copter.getRow(), copter.getColumn()) instanceof Obstacle)
            throw new CollisionException();
        else cave.setCell(copter);
    }

    public class CollisionException extends Exception {
        public CollisionException() {
            initializeCave();
        }
    }
}
