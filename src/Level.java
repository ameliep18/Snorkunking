
public class Level {

    private int idLevel;
    private double xLevel; //coordonnée x du centre du rectangle représentant le niveau
    private double yLevel;
    public TreasureChest chest;

    public TreasureChest getChest() {
        return chest;
    }

    public int getIdLevel() {
        return idLevel;
    }

    public double getXLevel() { return xLevel; }

    public double getYLevel() { return yLevel; }

    public Level(int idLevel, TreasureChest chest, double xLevel, double yLevel) {
        this.idLevel = idLevel;
        this.chest = chest;
        this.xLevel = xLevel;
        this.yLevel = yLevel;
    }


}


