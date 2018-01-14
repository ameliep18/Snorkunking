

import java.util.ArrayList;
import edu.princeton.cs.introcs.StdDraw;

import javax.sound.midi.Soundbank;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Diver {

    private String playerName;
    private int idPlayer;
    private double xDiver;
    private double yDiver;
    private Level playerLevel;
    private int score;

    public static ArrayList<Diver> playerList = new ArrayList<>();
    public ArrayList<TreasureChest> chestTransported = new ArrayList<>();
    public ArrayList<TreasureChest> depositList = new ArrayList<>();
    public ArrayList<Integer> scoreList = new ArrayList<>();



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

    public void setPlayerLevel(Level playerLevel) {
        this.playerLevel = playerLevel;
    }



    public static void game(int phase) {
        int n3 = Cave.NList.get(2);
        Main.cave3.getLevelList().get(n3).getChestList().clear();
        while (Reserve.oxygen > 0) {
            playerList.get(0).play(phase);
            playerList.get(1).play(phase);
        }
        if (Reserve.oxygen <= 0) {
            displayScore();//on calcule et affiche les scores

            //on fait tomber dans le dernier niveau de la cave3 les coffres que portaient les joueurs
            int N1 = playerList.get(0).getChestTransported().size(); //nb de coffre transportes par le joueur 1
            TreasureChest tmp;
            for (int i=0; i<N1; i++) {
                tmp=playerList.get(0).getChestTransported().get(i);
                Main.cave3.getLevelList().get(n3).getChestList().add(tmp);
                StdDraw.picture(690 - (i*50), Main.cave3.getLevelList().get(n3).getYLevel(), "coffre aux trésors.png", 25, 25);
            }
            int N2 = playerList.get(1).getChestTransported().size(); //nb de coffre transportes par le joueur 2
            for (int i=1; i<=N2; i++) {
                tmp=playerList.get(1).getChestTransported().get(i-1);
                Main.cave3.getLevelList().get(n3).getChestList().add(tmp);
                StdDraw.picture(530 - (i*50), Main.cave3.getLevelList().get(n3).getYLevel(), "coffre aux trésors.png", 25, 25);
            }
            /*int newPhase = phase++;
            if (newPhase <=3) {
                game(newPhase);
            }
            else {
                //fin du jeu : on affiche les scores et on désigne le vainqueur
                displayScore();
                int score1 = Diver.playerList.get(0).countFinalScore();
                int score2 = Diver.playerList.get(1).countFinalScore();
                if (score1>score2) {
                    System.out.println("Le gagnant est " + Diver.playerList.get(0).getPlayerName() + " !");
                }
                else if (score1==score2) {
                    System.out.println(Diver.playerList.get(0).getPlayerName() + " et " + Diver.playerList.get(1).getPlayerName() + " sont à égalité.");
                }
                else {
                    System.out.println("Le gagnant est " + Diver.playerList.get(1).getPlayerName() + " !");
                }
            }*/
        }



    }




    public void play(int phase) {
        //int n = Cave.NList.get(cave.getIdCave() - 1); //nombre de niveaux dans la cave
        int counter = 0;
        while (counter == 0) {
            if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN) == true) {
                System.out.println("Joueur " + this.getPlayerName()+ " descend");
                this.goDown(Main.caveList.get(this.getPlayerLevel().getIdCaveLevel()-1),this.getPlayerLevel());
                Reserve.oxygen = Reserve.oxygenConsumption(this.getChestTransported().size(),Reserve.oxygen);
                counter++;
                try { //fait en sorte que l'action ne s'effectue qu'une seule fois
                   Thread.sleep(250);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_UP) == true) {
                System.out.println("Joueur " + this.getPlayerName()+ " monte");
                this.goUp(Main.caveList.get(this.getPlayerLevel().getIdCaveLevel()-1),this.getPlayerLevel());
                Reserve.oxygen = Reserve.oxygenConsumption(this.getChestTransported().size(),Reserve.oxygen);
                counter++;
                try { //fait en sorte que l'action ne s'effectue qu'une seule fois
                   Thread.sleep(250);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (StdDraw.isKeyPressed(KeyEvent.VK_ENTER) == true) { //prendre un coffre dans son chestTransported
                System.out.println("Joueur " + this.getPlayerName()+ " prend le coffre");
                this.takeChest(Main.caveList.get(this.getPlayerLevel().getIdCaveLevel()-1),this.getPlayerLevel(),phase);
                Reserve.oxygen = Reserve.oxygenConsumption(0,Reserve.oxygen);
                counter++;
                try {  //fait en sorte que l'action de prendre le coffre ne s'effectue qu'une seule fois
                    Thread.sleep(250);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if ((StdDraw.isKeyPressed(KeyEvent.VK_SPACE) == true)) { //deposer le(s) coffre(s) dans le dépot
                System.out.println("Joueur " + this.getPlayerName()+ " dépose ses coffres");
                for (int i=0; i<this.chestTransported.size(); i++) {
                    TreasureChest tmp = chestTransported.get(i);
                    this.depositList.add(tmp);
                    this.countScore();
                    counter++;
                }
                    try {  //fait en sorte que l'action de déposer le coffre ne s'effectue qu'une seule fois
                        Thread.sleep(250);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                this.getChestTransported().clear();
                System.out.println(this.depositList);
                }
        }
    }


   public void takeChest(Cave cave, Level playerLevel, int phase) {
        if (playerLevel.getChest() != null) {
            this.getChestTransported().add(cave.getLevelList().get(playerLevel.getIdLevel()).getChest());
            playerLevel.setChest(null);
            deleteChest(cave,playerLevel);
            System.out.println("Joueur " + this.getPlayerName()+ " : " + this.getChestTransported());
        }
        else if (playerLevel.getIdCaveLevel() == 3 && playerLevel.getIdLevel() == Cave.NList.get(2)) { //si on est dans le dernier niveau de la cave3
            if (phase!=1) {
                Random rnd = new Random();
                int min = 0;
                int max = Main.cave3.getLevelList().get(Cave.NList.get(2)).getChestList().size();
                int k = rnd.nextInt(max - min + 1) + min;
                this.getChestTransported().add(Main.cave3.getLevelList().get(Cave.NList.get(2)).getChestList().get(k));
                playerLevel.setChest(null);
                deleteChest(cave,playerLevel);
            }
        }
        else {
            //on ajoute rien
        }
   }

   public void goDown(Cave cave, Level playerLevel) {
           this.deletePlayer(cave, playerLevel);
           int n = Cave.NList.get(cave.getIdCave() - 1);
           int idLevelDown = playerLevel.getIdLevel() + 1;

           if (idLevelDown <= n) {
               this.yDiver = cave.getLevelList().get(idLevelDown).getYLevel();
               this.setPlayerLevel(cave.getLevelList().get(idLevelDown));
           } else {
               int newIdCave = cave.getIdCave() + 1;
               if (newIdCave == 2) {
                   this.yDiver = Main.cave2.getLevelList().get(1).getYLevel();
                   this.setPlayerLevel(Main.cave2.getLevelList().get(1));
               } else if (newIdCave == 3) {
                   this.yDiver = Main.cave3.getLevelList().get(1).getYLevel();
                   this.setPlayerLevel(Main.cave3.getLevelList().get(1));
               } else {
                   // on est tout en bas, on ne fait donc rien
               }
           }
       StdDraw.picture(this.getXDiver(), this.getYDiver(), "Plongeur.jpg", 30, 30);
       StdDraw.show();
   }

   public void goUp(Cave cave, Level playerLevel) {
       this.deletePlayer(cave, playerLevel);
       int idLevelUp = playerLevel.getIdLevel() - 1;
       if (idLevelUp == 0) {
           if (cave.getIdCave() == 1) { //si on est en cave1, on arrivera au dépot
               this.yDiver = 725;
               this.setPlayerLevel(Main.cave1.getLevelList().get(0));
           }
           else {
               int newIdCave = cave.levelList.get(1).getIdCaveLevel() - 1;
               if (newIdCave == 1) {
                   int n1 = Cave.NList.get(0);
                   this.yDiver = Main.cave1.getLevelList().get(n1).getYLevel();
                   this.setPlayerLevel(Main.cave1.getLevelList().get(n1));
               }
               else if (newIdCave == 2) {
                   int n2 = Cave.NList.get(1);
                   this.yDiver = Main.cave2.getLevelList().get(n2).getYLevel();
                   this.setPlayerLevel(Main.cave2.getLevelList().get(n2));
               }
               else {
                   // on est tout en haut, on ne fait donc rien
               }
           }
       }
       else {
           this.yDiver = cave.getLevelList().get(idLevelUp).getYLevel();
           this.setPlayerLevel(cave.getLevelList().get(idLevelUp));
       }
       StdDraw.picture(this.getXDiver(), this.getYDiver(), "Plongeur.jpg", 30, 30);
       StdDraw.show();

   }

    private static void deleteChest(Cave cave, Level level) {
        if (cave.getIdCave() == 1) {
            int n = Cave.NList.get(0);
            double h = Main.cave1.getCaveHeight() / n;
            StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            StdDraw.filledRectangle(750,(700 - (h/2)- ((level.getIdLevel()-1) * h)), 12, 12);
        }
        if (cave.getIdCave() == 2) {
            int n = Cave.NList.get(1);
            double h = Main.cave2.getCaveHeight() / n;
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.filledRectangle(750,(375 - (h/2)- ((level.getIdLevel()-1) * h)), 12, 12);
        }
        if (cave.getIdCave() == 3) {
            int n = Cave.NList.get(2);
            double h = Main.cave3.getCaveHeight() / n;
            StdDraw.setPenColor(StdDraw.DARK_GRAY);
            StdDraw.filledRectangle(750,(150 - (h/2)- ((level.getIdLevel()-1) * h)), 12, 12);
        }
        StdDraw.show();

    }

    private void deletePlayer(Cave cave, Level level) {
        if (level.getIdLevel() == 0) {
            int n = Cave.NList.get(0);
            double h = Main.cave1.getCaveHeight() / n;
            StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
            StdDraw.filledRectangle(this.getXDiver(),(710 - (h/2)- ((level.getIdLevel()-1) * h)), 20, 18);
        }
        else {
            if (cave.getIdCave() == 1) {
                int n = Cave.NList.get(0);
                double h = Main.cave1.getCaveHeight() / n;
                StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
                StdDraw.filledRectangle(this.getXDiver(),(700 - (h/2)- ((level.getIdLevel()-1) * h)), 15, 10);
            }
            if (cave.getIdCave() == 2) {
                int n = Cave.NList.get(1);
                double h = Main.cave2.getCaveHeight() / n;
                StdDraw.setPenColor(StdDraw.GRAY);
                StdDraw.filledRectangle(this.getXDiver(),(375 - (h/2)- ((level.getIdLevel()-1) * h)), 15, 10);
            }
            if (cave.getIdCave() == 3) {
                int n = Cave.NList.get(2);
                double h = Main.cave3.getCaveHeight() / n;
                StdDraw.setPenColor(StdDraw.DARK_GRAY);
                StdDraw.filledRectangle(this.getXDiver(),(150 - (h/2)- ((level.getIdLevel()-1) * h)), 15, 10);
            }
        }
        StdDraw.show();
    }

    public void countScore() {
        for (int i=0; i<this.depositList.size(); i++) {
            int nbTreasures = this.depositList.get(i).getTreasures();
            this.scoreList.add(nbTreasures);
        }
    }

    public int countFinalScore() {
        int score = 0;
        for (int i=0; i<this.scoreList.size(); i++) {
            score = score + this.scoreList.get(i);
        }
        System.out.println(this.scoreList);
        return score;
    }

    public static void displayScore() {
        for (int i=0; i<Diver.playerList.size(); i++) {
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.text(70, 810 - i*40, "Score : " + Integer.toString(Diver.playerList.get(i).countFinalScore())); //score à afficher
        }
    }



}
