package helicopter;

public class Obstacle extends Sprite {
    public Obstacle(int row, int col) {
        super(GUI.OBSTACLE, row, col);
    }

    public void moveAlong() {
        column--;
    }

    public void transport(int newRow, int newCol) {
        row = newRow;
        column = newCol;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public char getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
