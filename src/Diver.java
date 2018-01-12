import java.util.ArrayList;

public class Diver {

    private String playerName;
    public static ArrayList<Diver> diverList = new ArrayList<>();
    public static ArrayList<Diver> IAList = new ArrayList<>();



    public Diver(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

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
       TreasureChest.chestTransported.add(cave.getLevelList().get(level.getIdLevel()).getChest());
   }


}
