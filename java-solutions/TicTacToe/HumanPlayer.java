package TicTacToe;

import java.io.IOException;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.SortedSet;


public class HumanPlayer implements Player {
    private static final PrintStream out = new PrintStream(System.out);
    private static final Scanner in = new Scanner(System.in);

    private static int intInput(String output) {
        int check;
        while (true) {
            try {
                out.println(output);
                check = in.nextInt();
                break;
            } catch (RuntimeException e) {
                out.println("Invalid input! Try again");
                out.println();
                System.exit(0);
            }
        }
        return check;
    }

    @Override
    public Move move(final Board board, final Cell cell, final String name) {
        while (true) {
            out.println(name + "'s move");
            out.println("Enter row and column. If you want to give up input \"Give up\"");
            int row = intInput("Row: ");
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
