import java.util.ArrayList;
import java.util.Random;

public class Cave {

    private int idCave;
    private double caveHeight;
    private int nbLevels;

    public ArrayList<Level> levelList = new ArrayList<>();
    public static ArrayList<Integer> NList = new ArrayList<>();

    public int getIdCave() {
        return idCave;
    }
    public double getCaveHeight() { return caveHeight; }
    public ArrayList<Level> getLevelList() { return levelList; }


    public Cave(int idCave, double caveHeight) {
        this.idCave = idCave;
        this.caveHeight = caveHeight; //hauteur de la cave
        int n = this.nbLevels();
        Cave.NList.add(n);
        double h = caveHeight/n; //hauteur des niveaux
        double beginning = 0;
        ArrayList<Level> levelList = this.levelList;
        if (idCave ==1) {
            beginning = 700;
            levelList.add(Main.deposit);
        }
        if (idCave == 2) {
            beginning = 375;
            levelList.add(null);
        }
        if (idCave == 3) {
            beginning = 150;
            levelList.add(null);
        }
        for (int i=0; i< n; i++) {
            TreasureChest chest = new TreasureChest(i+1,treasuresPerChest()); //création des coffres
            Level level = new Level(idCave,i+1, chest, 400,beginning - (i*h) - (h/2)); //création des niveaux
            levelList.add(level);
        }
        //System.out.println(n);
        //System.out.println(NList);
    }


	public int nbLevels() { //renvoie le nombre de niveaux par cave
	    int caveNbLevels = 0;
        int minLevel;
        int maxLevel;

        if (this.getIdCave() == 1) {
            Random rnd = new Random();
            minLevel = 9;
            maxLevel = 12;
            caveNbLevels = rnd.nextInt(maxLevel - minLevel + 1) + minLevel;
        }
        if (this.getIdCave() == 2) {
            Random rnd = new Random();
            minLevel = 6;
            maxLevel = 9;
            caveNbLevels = rnd.nextInt(maxLevel - minLevel + 1) + minLevel;
        }
        if (this.getIdCave() == 3) {
            Random rnd = new Random();
            minLevel = 3;
            maxLevel = 6;
            caveNbLevels = rnd.nextInt(maxLevel - minLevel + 1) + minLevel;
        }
        return caveNbLevels;
    }

    public int treasuresPerChest() { //determine le nombre de tresors dans chaque coffre selon la cave dans laquelle ils se trouvent
        int minTreasure = 0;
        int maxTreasure = 0;
        int caveNbTreasurePerChest;

        if (this.getIdCave() == 1) {
            minTreasure = 1;
            maxTreasure = 3;
        }
        if (this.getIdCave() == 2) {
            minTreasure = 5;
            maxTreasure = 8;
        }
        if (this.getIdCave() == 3) {
            minTreasure = 10;
            maxTreasure = 12;
        }

        Random rnd1 = new Random();
        caveNbTreasurePerChest = rnd1.nextInt(maxTreasure-minTreasure+1) + minTreasure;
        return caveNbTreasurePerChest;
    }

} //ferme la classe
