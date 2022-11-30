package TicTacToe;

public class NullPlayer implements Player {
    @Override
    public Move move(Board board, Cell cell, String name) {
        return null;
    }
}
