package game;

import java.util.Map;

public class BoardMNK {
    private static  Board board;
    public static  Map<Cell, Character> SYMBOLS;
    public BoardMNK(int m, int n, int k) {
        this.board = new Board(m, n, k);
    }
    public Board getBoard() {
        return new Board(board);
    }
    public static int isValid(Move move) {
        if (move.getRow() >= board.getN() || move.getRow() < 0 || move.getCol() >= board.getM() || move.getCol() < 0) {
            return -1;
        } else if (board.getCell(move.getRow(), move.getCol()) != Cell.E){
            return -2;
        } else {
            return 1;
        }
    }
    private static int checkMove(Move move, int right, int up, int down) {
        int r = move.getRow();
        int c = move.getCol();
        int count = -1;
        while(r >= 0 && c >= 0 && r < board.getN() && c < board.getM() && board.getBrd()[r][c] == move.getCell()) {
            count++;
            r += right;
            c += up;
            c -= down;
        }
        r = move.getRow();
        c = move.getCol();
        while(r >= 0 && c >= 0 && r < board.getN() && c < board.getM() && board.getBrd()[r][c] == move.getCell()) {
            count++;
            r -= right;
            c -= up;
            c += down;
        }
        return count;

    }
    public static Result makeMove(Move move) {
        if (isValid(move) < 0) {
            return Result.LOSE;
        }
        board.setCell(move);
        board.decreaseEmptyCells();
        int[] strikes = new int[4];
        strikes[0] = checkMove(move, 1,  0, 0);
        strikes[1] = checkMove(move, 0, 1, 0);
        strikes[2] = checkMove(move, 1,  1, 0);
        strikes[3] = checkMove(move, 1, 0, 1);
        for (int i = 0; i < 4; i++) {
            if (strikes[i] >= board.getK()) {
                return Result.WIN;
            }
        }
        if (board.getEmptyCells() == 0) {
            return Result.DRAW;
        }
        for (int i = 0; i < 4; i++) {
            if (strikes[i] >= 4) {
                return Result.STRIKE;
            }
        }
        return Result.UNKNOWN;
    }
    @Override
    public String toString() {
        int firstCellLenght = String.valueOf(board.getN()).length();
        int cellLenght = String.valueOf(board.getM()).length();
        StringBuilder sb = new StringBuilder(" ");
        for(int j = 0; j < firstCellLenght; j++) {
            sb.append(" ");
        }
        for (int i = 0; i < board.getM(); i++) {
            sb.append(i);
            for(int j = 0; j < cellLenght - String.valueOf(i).length() + 1; j++) {
                sb.append(" ");
            }
        }
        for (int i = 0; i < board.getN(); i++) {
            sb.append("\n");
            sb.append(i + " ");
            for(int j = 0; j < firstCellLenght - String.valueOf(i).length(); j++) {
                sb.append(" ");
            }
            for (int j = 0; j < board.getM(); j++) {
                sb.append(SYMBOLS.get(board.getCell(i, j)));
                for (int k = 0; k < cellLenght; k++){
                    sb.append(" ");
                }
            }
        }
        return sb.toString();
    }
}
