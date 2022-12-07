package game;

public class PlayerInfo {
    private final Player player;
    private final String  name;
    private final Cell cell;
    private boolean alive;
    PlayerInfo(Player player, String name, Cell cell) {
        this.player = player;
        this.name = name;
        this.cell = cell;
        this.alive = true;
    }

    public Player getPlayer() {
        return player;
    }
    public String getName() {
        return name;
    }

    public Cell getCell() {
        return cell;
    }

    public boolean isAlive() {
        return alive;
    }
    public void lost() {
        this.alive = false;
    }
}
