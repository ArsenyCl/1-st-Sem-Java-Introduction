package TicTacToe;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    ArrayList<PlayerInfo> players;
    private final PrintStream out = System.out;
    public Game(ArrayList<PlayerInfo> players) {
        this.players = players;
    }
    public int play(BoardMNK board) {
        out.println("Current position");
        out.println(board);
        out.println();
        int playersLeft = players.size();
        main: while (true) {
            for (int i = 0; i < players.size(); i++) {
                if(players.get(i).isAlive()) {
                    int result = move(board, players.get(i));
                    if (result == 2) {
                        out.println(players.get(i).getName() + " won! Congratulations!");
                        out.println("Current position");
                        out.println(board);
                        return i;
                    } else if(result == 0) {
                        out.println("Draw! No winners, no losers!");
                        out.println("Current position");
                        out.println(board);
                        return -1;
                    } else if (result == 3) {
                        out.println("Strike! " + players.get(i).getName() + "'s turn again!" );
                        out.println("Current position");
                        out.println(board);
                        out.println();
                        i--;
                        continue;
                    } else if(result == -1) {
                        out.println("Unfortunately " + players.get(i).getName() + " leaves the game. GG!");
                        players.get(i).lost();
                        playersLeft--;
                        if (playersLeft == 1) {
                            for (int j = 0; j < players.size(); j++) {
                                if (players.get(j).isAlive()) {
                                    out.println();
                                    out.println(players.get(j).getName() + " won! Congratulations!");
                                    return j;
                                }
                            }
                        }
                    }
                }
                out.println("Current position");
                out.println(board);
                out.println();
            }
        }
    }
    public int move(final BoardMNK board, final PlayerInfo player) {
        try {
            Move move = player.getPlayer().move(board.getBoard(), player.getCell(), player.getName());
            Result result = BoardMNK.makeMove(move);
            if (result == Result.WIN) {
                return 2;
            }
            if (result == Result.DRAW) {
                return 0;
            }
            if (result == Result.LOSE) {
                return -1;
            }
            if (result == Result.STRIKE) {
                return 3;
            }
            return 1;
        } catch (RuntimeException e) {
            return -1;
        }

    }
}
