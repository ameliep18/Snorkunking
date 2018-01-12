import java.util.ArrayList;
import java.util.Random;

public class TreasureChest {

    public void getTreasures(int treasures) {
        this.treasures = treasures;
    }

    private int treasures;
	private double x = 750;
	private double y;

	public TreasureChest(int treasures, double y) {
        this.treasures = treasures;
        this.y = y;
    }

    public int getTreasures() {
        return treasures;
    }
}
