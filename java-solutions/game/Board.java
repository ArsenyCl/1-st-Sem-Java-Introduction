package game;

import java.util.Arrays;

public class Board implements Position {
    private Cell[][] brd;
    private final int m;
    private final int n;
    private final int k;

    private int emptyCells;

    public Board(int m, int n, int k) {
        brd = new Cell[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(brd[i], Cell.E);
        }
        this.m = m;
        this.n = n;
        this.k = k;
        this.emptyCells = m * n;
    }

    public Board(Board board) {
        brd = new Cell[board.getN()][];
        for (int i = 0; i < board.getN(); i++) {
            brd[i] = board.getBrd()[i].clone();
        }
        this.n = board.getN();
        this.m = board.getM();
        this.k = board.getK();
        this.emptyCells = board.getEmptyCells();
    }

    public Cell[][] getBrd() {
        return brd;
    }

    @Override
    public void setCell(Move move) {
        brd[move.getRow()][move.getCol()] = move.getCell();
    }

    @Override
    public Cell getCell(int row, int col) {
        return brd[row][col];
    }

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

    public int getK() {
        return k;
    }

    public int getEmptyCells() {
        return emptyCells;
    }

    public void decreaseEmptyCells() {
        emptyCells--;
    }
}
