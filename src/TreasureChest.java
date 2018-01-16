
public class TreasureChest {

    private int treasures;
    private double xChest;


    public TreasureChest(int treasures, int xChest) {
        this.treasures = treasures;
        this.xChest = xChest;
    }

    public int getTreasures() {
        return treasures;
    }

    public double getXChest() {
        return xChest;
    }

    public void setXChest(double x) {
        this.xChest = x;
    }


}
