package TicTacToe;

import java.io.PrintStream;
import java.util.*;

public class Tournament {
    private static final char[] symbolsToChoose = new char[]{'X', 'O', '+', '/'};
    private static char[] symbs;
    private static final Cell[] cells = new Cell[]{Cell.X, Cell.O, Cell.S, Cell.T};
    private static final PrintStream out = new PrintStream(System.out);
    private static final Scanner in = new Scanner(System.in);

    public Tournament(int games) {
        tournament(games);
    }
    private void changeOrder(ArrayList<PlayerInfo> players) {
        players.add(players.get(0));
        players.remove(0);
    }
    private void tournament(int games) {
        if (games > 1) {
            games = intInput("How many wins you want to reach: ");
            ArrayList<PlayerInfo> players = gameSettings();
            int gamesWon[] = new int[players.size()];
            int max = 0;
            int i = 0;
            while (max < games) {
                out.println("Game №" + (i+1));
                out.println();
                Game game = new Game((ArrayList<PlayerInfo>) players.clone());
                BoardMNK board = boardSettings();
                int gameRes = game.play(board);
                gamesWon[gameRes]++;
                max = Math.max(gamesWon[gameRes], max);
                i++;
                out.println("Score: ");
                for (int j = 0; j < players.size(); j++) {
                    out.println(players.get(j).getName() + ": " + gamesWon[j]);
                }
                out.println();
                changeOrder(players);
            }
            for (i = 0; i < gamesWon.length; i++) {
                if (gamesWon[i] == games) {
                    out.println(players.get(i).getName() + " WON!!!!! CONGRATULATIONS!!!!!");
                    break;
                }
            }
        } else {
            Game game = new Game(gameSettings());
            BoardMNK board = boardSettings();
            game.play(board);
        }
    }
    private ArrayList<PlayerInfo> gameSettings() {
        BoardMNK.SYMBOLS = new HashMap<>();
        BoardMNK.SYMBOLS.put(Cell.E, '.');
        symbs = symbolsToChoose.clone();
        int playersCount = intInput("How many players take part in this game: ");
        ArrayList<PlayerInfo> players = new ArrayList<>();
        for (int i = 1; i <= playersCount; i++) {
            players.add(playerSettings(i));
        }
        return players;
    }
    private PlayerInfo playerSettings(int i) {
        Player player;
        while (true) {
            out.println("Player №" + i + " type: ");
            out.println("1 - Real person");
            out.println("2 - Random-move bot");
            out.println("3 - Sequential-move bot");
            String typeCheck = in.nextLine();
            if (typeCheck.equals("1")) {
                player = new HumanPlayer();
                break;
            } else if (typeCheck.equals("2") ) {
                player = new RandomPlayer();
                break;
            } else if (typeCheck.equals("3")) {
                player = new SequentialPlayer();
                break;
            }
            out.println("Invalid input! Try again");
            out.println();
        }
        out.println("Player type accepted.");
        out.println("Player №" + i + " name: ");
        String name = in.nextLine();
        out.println("Player name accepted.");
        while (true) {
            out.println("Player №" + i + " cell: ");
            for (int j = 0; j  < symbs.length; j++) {
                out.println((j+1) +  " - " + symbs[j]);
            }
            String cellCheck = in.nextLine();
            if (cellCheck.length() == 1 && Character.isDigit(cellCheck.charAt(0)) && Integer.parseInt(cellCheck) <= symbs.length &&
                    Integer.parseInt(cellCheck) > 0) {
                BoardMNK.SYMBOLS.put(cells[i-1], symbs[Integer.parseInt(cellCheck)-1]);
                for(int j = Integer.parseInt(cellCheck); j < symbs.length; j++) {
                    symbs[j-1] = symbs[j];
                }
                symbs = Arrays.copyOf(symbs, symbs.length -1);
                break;
            }
            out.println("Invalid input! Try again");
            out.println();
        }
        out.println("Player cell accepted.");
        return new PlayerInfo(player, name, cells[i-1]);
    }
    private BoardMNK boardSettings() {
        out.println("Enter board size");
        int rows = intInput("Rows: ");
        int cols = intInput("Coloumns: ");
        int k = intInput("Cells to win: ");
        return new BoardMNK(cols, rows, k);
    }
    private static int intInput(String output) {
        main: while (true) {
            out.println(output);
            String check = in.nextLine();
            for (int i = 0; i < check.length(); i++) {
                if (!Character.isDigit(check.charAt(i))) {
                    out.println("Invalid input! Try again");
                    out.println();
                    continue main;
                }
            }
            return Integer.parseInt(check);
        }
    }
}
