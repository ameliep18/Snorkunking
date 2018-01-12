

import java.util.ArrayList;

public class Diver {

    private String playerName;
    private double xDiver;
    private double yDiver;
    private Level playerLevel;

    public static ArrayList<Diver> diverList = new ArrayList<>();
    public static ArrayList<Diver> IAList = new ArrayList<>();



    public Diver(String playerName, double x, double y) {
        this.playerName = playerName;
        this.xDiver = x;
        this.yDiver = y;
    }

    public String getPlayerName() {
        return playerName;
    }

    public double getXDiver() { return xDiver;}

    public double getYDiver() { return yDiver;}

    public static final int VK_ENTER = 10;
    public static final int VK_KP_DOWN = 225;
    public static final int VK_KP_UP = 224;

   /*public void transport(int idCave) {
        int chestTransported = 0;
        int level = 0;
        ArrayList<TreasureChest> nbChestTransported = new ArrayList<>();
        int n = Cave.nbLevels(idCave);
        System.out.println(n);

        while (level <= n) {
            if (isKeyPressed(VK_KP_DOWN) == true) { //on commence forcement par descendre dans le premier niveau de la Cave1

                if (isKeyPressed(VK_ENTER) == true) { //soit on prend le coffre de ce niveau
                    nbChestTransported.add(Cave.levelList.get(level - 1));
                    chestTransported++;
                }
                if (isKeyPressed(VK_KP_DOWN) == true) { //soit on descend plus bas
                    level++;
                }
            }
        }

    }*/

   public void takeChest(Cave cave, Level level) {
       TreasureChest.chestTransported.add(cave.getLevelList().get(level.getIdLevel()-1).getChest());
       System.out.println(TreasureChest.chestTransported);
   }

   public void goDown(Cave cave, Level playerLevel) {
       int idLevelDown = playerLevel.getIdLevel() + 1;
       if (idLevelDown < cave.getNList().get(cave.getIdCave()-1)) { //si idLevelDown < n
           xDiver = cave.getLevelList().get(idLevelDown-1).getXLevel();
           yDiver = cave.getLevelList().get(idLevelDown-1).getYLevel();
       }
       else {
           int newIdCave = cave.getIdCave() + 1;
           if (newIdCave == 2) {
               goDown(Main.cave2, Main.cave2.getLevelList().get(0));
           }
           else if (newIdCave == 3) {
               goDown(Main.cave3, Main.cave3.getLevelList().get(0));
           }
           else {
               // on est tout en bas, on ne fait donc rien
           }
       }
   }

   public void goUp(Cave cave, Level playerLevel) {
       int idLevelUp = playerLevel.getIdLevel() - 1;
       if (idLevelUp == 1) {
           if (cave.getIdCave() == 1) { //on arrivera au dépot
               xDiver = 200; //à bidouiller
               yDiver = 725;
           }
           else {
               int newIdCave = cave.getIdCave() - 1;
               if (newIdCave == 1) {
                   goUp(Main.cave1, Main.cave1.getLevelList().get(Main.cave1.getNList().get(0)-1));
               }
               else if (newIdCave == 2) {
                   goUp(Main.cave2, Main.cave2.getLevelList().get(Main.cave2.getNList().get(1)-1));
               }
               else {
                   // on est tout en haut, on ne fait donc rien
               }
           }
       }
       else {
           xDiver = cave.getLevelList().get(idLevelUp - 1).getXLevel();
           yDiver = cave.getLevelList().get(idLevelUp - 1).getYLevel();
       }

   }



}
