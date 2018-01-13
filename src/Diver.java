

import java.util.ArrayList;
import edu.princeton.cs.introcs.StdDraw;
import java.awt.event.KeyEvent;

public class Diver {

    private String playerName;
    private int idPlayer;
    private double xDiver;
    private double yDiver;
    private Level playerLevel;

    public static ArrayList<Diver> diverList = new ArrayList<>();
    public static ArrayList<Diver> IAList = new ArrayList<>();
    public ArrayList<TreasureChest> chestTransported = new ArrayList<>();



    public Diver(String playerName, int idPlayer, double x, double y, Level playerLevel) {
        this.playerName = playerName;
        this.idPlayer = idPlayer;
        this.xDiver = x;
        this.yDiver = y;
        this.playerLevel = playerLevel;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public double getXDiver() { return xDiver;}

    public double getYDiver() { return yDiver;}

    public Level getPlayerLevel() {
        return playerLevel;
    }

    public ArrayList<TreasureChest> getChestTransported() { return chestTransported;}


   public void play(Cave cave, int phase) {
       int n = Cave.NList.get(cave.getIdCave() - 1); //nombre de niveaux dans la cave
       int oxygen = 2*n; //initialisation de la réserve d'oxygène
       while (oxygen > 0) {
           if (StdDraw.isKeyPressed(KeyEvent.VK_KP_DOWN) == true) {
               this.goDown(cave,this.getPlayerLevel());
               oxygen = Reserve.oxygenConsuption(this.getChestTransported().size(),oxygen);
           }
           if (StdDraw.isKeyPressed(KeyEvent.VK_KP_UP) == true) {
               this.goUp(cave,this.getPlayerLevel());
               oxygen = Reserve.oxygenConsuption(this.getChestTransported().size(),oxygen);
           }
           if (StdDraw.isKeyPressed(KeyEvent.VK_ENTER) == true) {
               this.takeChest(cave,this.getPlayerLevel());
               oxygen = Reserve.oxygenConsuption(0,oxygen);
           }
       }
       if (oxygen <= 0) {
           int newPhase = phase+1;
           this.play(cave,newPhase);
       }
   }

   public void takeChest(Cave cave, Level level) {
       this.getChestTransported().add(cave.getLevelList().get(level.getIdLevel()-1).getChest());
       System.out.println(this.getChestTransported());
       deleteChest(cave,level);
   }

   public void goDown(Cave cave, Level playerLevel) {
        int n = Cave.NList.get(cave.getIdCave()-1);
        int idLevelDown = playerLevel.getIdLevel() + 1;
        if (idLevelDown < n) {
            this.xDiver = cave.getLevelList().get(idLevelDown-1).getXLevel();
            this.yDiver = cave.getLevelList().get(idLevelDown-1).getYLevel();
        }
        else {
            int newIdCave = cave.getIdCave() + 1;
            if (newIdCave == 2) {
                this.xDiver = Main.cave2.getLevelList().get(0).getXLevel();
                this.yDiver = Main.cave2.getLevelList().get(0).getYLevel();

            }
            else if (newIdCave == 3) {
                this.xDiver = Main.cave3.getLevelList().get(0).getXLevel();
                this.yDiver = Main.cave3.getLevelList().get(0).getYLevel();
           }
           else {
               // on est tout en bas, on ne fait donc rien
           }
        }
        StdDraw.picture(this.getXDiver(), this.getYDiver(), "Plongeur.jpg", 30, 30);
        StdDraw.show();
   }

   public void goUp(Cave cave, Level playerLevel) {
       int idLevelUp = playerLevel.getIdLevel() - 1;
       if (idLevelUp == 0) {
           if (cave.getIdCave() == 1) { //on arrivera au dépot
               this.xDiver = 200; //à bidouiller
               this.yDiver = 725;
           }
           else {
               int newIdCave = cave.getIdCave() - 1;
               if (newIdCave == 1) {
                   int n1 = Cave.NList.get(0);
                   this.xDiver = Main.cave1.getLevelList().get(n1-1).getXLevel();
                   this.yDiver = Main.cave1.getLevelList().get(n1-1).getYLevel();
               }
               else if (newIdCave == 2) {
                   int n2 = Cave.NList.get(1);
                   this.xDiver = Main.cave2.getLevelList().get(n2-1).getXLevel();
                   this.yDiver = Main.cave2.getLevelList().get(n2-1).getYLevel();
               }
               else {
                   // on est tout en haut, on ne fait donc rien
               }
           }
       }
       else {
           this.xDiver = cave.getLevelList().get(idLevelUp - 1).getXLevel();
           this.yDiver = cave.getLevelList().get(idLevelUp - 1).getYLevel();
       }
       StdDraw.picture(this.getXDiver(), this.getYDiver(), "Plongeur.jpg", 30, 30);
       StdDraw.show();

   }

    private static void deleteChest(Cave cave, Level level) {
        if (cave.getIdCave() == 1) {
            int n = Cave.NList.get(0);
            double h = Main.cave1.getCaveHeight() / n;
            StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            StdDraw.filledRectangle(750,(700 - (h/2)- ((level.getIdLevel()-1) * h)), 14, 14);
        }
        if (cave.getIdCave() == 2) {
            int n = Cave.NList.get(1);
            double h = Main.cave2.getCaveHeight() / n;
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.filledRectangle(750,(375 - (h/2)- ((level.getIdLevel()-1) * h)), 14, 14);
        }
        if (cave.getIdCave() == 3) {
            int n = Cave.NList.get(2);
            double h = Main.cave3.getCaveHeight() / n;
            StdDraw.setPenColor(StdDraw.DARK_GRAY);
            StdDraw.filledRectangle(750,(150 - (h/2)- ((level.getIdLevel()-1) * h)), 14, 14);
        }
        StdDraw.show();

    }



}
