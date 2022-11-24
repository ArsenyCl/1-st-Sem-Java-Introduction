package TicTacToe;

import java.io.PrintStream;
import java.util.Scanner;


public class HumanPlayer implements Player {
    private static final PrintStream out = new PrintStream(System.out);
    private static final Scanner in = new Scanner(System.in);

    private static int intInput(String output) {
        int attempts = 10;
        main: while (attempts > 0) {
            out.println(output);
            String check = in.nextLine();
            if (check.isEmpty()) {
                continue;
            }
            if (!check.equals("Give up")) {
                for (int i = 0; i < check.length(); i++) {
                    if (!Character.isDigit(check.charAt(i))) {
                        attempts--;
                        out.println("Invalid input! Try again. You have " + i + " attempts left!");
                        out.println();
                        continue main;
                    }
                }
                return Integer.parseInt(check);
            } else {
                break;
            }
        }
        return -1;
    }
    @Override
    public Move move(final Board board, final Cell cell, final String name) {
        while (true) {
            out.println(name + "'s move");
            out.println("Enter row and column. If you want to give up input \"Give up\"");
            int row = intInput("Row: ");
            if (row == -1) {
                return new Move(-1, -1, cell);
            }
            int col = intInput("Col ");
            final Move move = new Move(row, col, cell);
            int moveResult = BoardMNK.isValid(move);
            if (moveResult > 0) {
                return move;
            } else if (moveResult == -2) {
                out.println("Move " + move + " is invalid. Sell is reserved!");
            } else if (moveResult == -1) {
                out.println("Move " + move + " is invalid. You have left board boarders!");
            }
        }
    }
}
