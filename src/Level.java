
public class Level {

    private int idLevel;
    public TreasureChest chest;

    public TreasureChest getChest() {
        return chest;
    }

    public int getIdLevel() {
        return idLevel;
    }


    public Level(int idLevel, TreasureChest chest) {
        this.idLevel = idLevel;
        this.chest = chest;
    }
}


