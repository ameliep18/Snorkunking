import java.util.ArrayList;

public class Level {

    private int idCaveLevel;
    private int idLevel;
    private double xLevel; //coordonnée x du centre du rectangle représentant le niveau
    private double yLevel;
    public TreasureChest chest;
    private ArrayList<TreasureChest> chestList = new ArrayList<>();

    public TreasureChest getChest() {
        return chest;
    }
    public void setChest(TreasureChest chest) {
        this.chest = chest;
    }

    public int getIdLevel() {
        return idLevel;
    }

    public int getIdCaveLevel() {
        return idCaveLevel;
    }

    public ArrayList<TreasureChest> getChestList() {
        return chestList;
    }

    public double getXLevel() { return xLevel; }

    public double getYLevel() { return yLevel; }

    public Level(int idCaveLevel,int idLevel, TreasureChest chest, double xLevel, double yLevel) {
        this.idCaveLevel = idCaveLevel;
        this.idLevel = idLevel;
        this.chest = chest;
        this.xLevel = xLevel;
        this.yLevel = yLevel;
    }



}


