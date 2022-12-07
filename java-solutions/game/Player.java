package game;

public interface Player {
    Move move(Board board, Cell cell, String name);
}