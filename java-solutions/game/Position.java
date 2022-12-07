package game;

public interface Position {

    Cell[][] getBrd();
    void setCell(Move move);
    Cell getCell(int row, int col);
}
