package TicTacToe;


import java.io.PrintStream;

public class SequentialPlayer implements Player {
    private final PrintStream out = System.out;
    @Override
    public Move move(final Board board, final Cell cell, final String name) {
        for (int r = 0; r < board.getN(); r++) {
            for (int c = 0; c < board.getN(); c++) {
                final Move move = new Move(r, c, cell);
                if (BoardMNK.isValid(move) > 0) {
                    out.println(name + "'s move");
                    return move;
                }
            }
        }
        throw new IllegalStateException("No valid moves");
    }
}
