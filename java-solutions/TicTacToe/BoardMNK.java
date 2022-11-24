package TicTacToe;

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
    public static Result makeMove(Move move) {
        if (isValid(move) < 0) {
            return Result.LOSE;
        }
        board.setCell(move);
        board.decreaseEmptyCells();
        int count = -1;
        int[] strikes = new int[4];
        int r = move.getRow();//check Verical
        int c = move.getCol();
        while(r < board.getN() && board.getBrd()[r][c] == move.getCell()) {
            count++;
            r++;
        }
        r = move.getRow();
        c = move.getCol();
        while(r >= 0  && board.getBrd()[r][c] == move.getCell()) {
            count++;
            r--;
        }
        strikes[0] = count;
        if (count >= board.getK()) {
            return Result.WIN;
        }

        count = -1;
        r = move.getRow();//check Horizontal
        c = move.getCol();
        while(c < board.getM() && board.getBrd()[r][c] == move.getCell()) {
            count++;
            c++;
        }
        r = move.getRow();
        c = move.getCol();
        while(c >= 0  && board.getBrd()[r][c] == move.getCell()) {
            count++;
            c--;
        }
        strikes[1] = count;
        if (count >= board.getK()) {
            return Result.WIN;
        }

        count = -1;
        r = move.getRow();//check Diagonal from left bottom to right top
        c = move.getCol();
        while(r >= 0 && c < board.getM() && board.getBrd()[r][c] == move.getCell()) {
            count++;
            r--;
            c++;
        }
        r = move.getRow();
        c = move.getCol();
        while(r < board.getN()  && c >= 0  && board.getBrd()[r][c] == move.getCell()) {
            count++;
            r++;
            c--;
        }
        strikes[2] = count;
        if (count >= board.getK()) {
            return Result.WIN;
        }

        count = -1;
        r = move.getRow();//check Diagonal from right bottom to left top
        c = move.getCol();
        while(r < board.getN() && c < board.getM() && board.getBrd()[r][c] == move.getCell()) {
            count++;
            r++;
            c++;
        }
        r = move.getRow();
        c = move.getCol();
        while(r >= 0 && c >= 0  && board.getBrd()[r][c] == move.getCell()) {
            count++;
            r--;
            c--;
        }
        strikes[3] = count;
        if (count >= board.getK()) {
            return Result.WIN;
        }
        for (int i = 0; i < 4; i++) {
            if (strikes[i] >= 4) {
                return Result.STRIKE;
            }
        }
        if (board.getEmptyCells() == 0) {
            return Result.DRAW;
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
