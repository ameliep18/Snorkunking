import java.util.ArrayList;
import java.util.Random;

public class TreasureChest {

    private int idChest;
    private int treasures;
    public static ArrayList<TreasureChest> chestTransported = new ArrayList<>();


	public TreasureChest(int idChest, int treasures) {
        this.treasures = treasures;
    }

    public int getTreasures() {
        return treasures;
    }
}