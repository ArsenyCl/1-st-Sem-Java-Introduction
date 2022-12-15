package game;

import java.io.PrintStream;
import java.util.Random;


public class RandomPlayer implements Player {
    private final Random random;

    public RandomPlayer(final Random random) {
        this.random = random;
    }

    private final PrintStream out = System.out;

    public RandomPlayer() {
        this(new Random());
    }

    @Override
    public Move move(final Board board, Cell cell, String name) {
        while (true) {
            int r = random.nextInt(board.getN());
            int c = random.nextInt(board.getM());
            final Move move = new Move(r, c, cell);
            if (BoardMNK.isValid(move) > 0) {
                out.println(name + "'s move");
                return move;
            }
        }
    }
}
