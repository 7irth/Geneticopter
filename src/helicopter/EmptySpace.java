package helicopter;

public class EmptySpace extends Sprite {
    public EmptySpace(int row, int col) {
        super(GUI.EMPTY, row, col);
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
