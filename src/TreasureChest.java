
public class TreasureChest {

    private int xChest;
    private int treasures;


    public TreasureChest(int xChest, int treasures) {
        this.xChest = xChest;
        this.treasures = treasures;
    }

    public int getTreasures() {
        return treasures;
    }


    public int getXChest() {
        return xChest;
    }

    public void setXChest(int xChest) {
        this.xChest = xChest;
    }
}
