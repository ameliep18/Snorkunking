import java.util.ArrayList;

public class Level {

    private int idCaveLevel;
    private int idLevel;
    private double xLevel; //coordonnée x du centre du rectangle représentant le niveau
    private double yLevel;
    private TreasureChest chest;
    public ArrayList<TreasureChest> chestList = new ArrayList<>();

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

    public double getYLevel() { return yLevel; }

    public void setYLevel(double yLevel) { this.yLevel = yLevel; }

    public void setIdLevel(int idLevel) {
        this.idLevel = idLevel;
    }

    public Level(int idCaveLevel,int idLevel, TreasureChest chest, double xLevel, double yLevel) {
        this.idCaveLevel = idCaveLevel;
        this.idLevel = idLevel;
        this.chest = chest;
        this.xLevel = xLevel;
        this.yLevel = yLevel;
    }



}


