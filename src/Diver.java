

import java.util.ArrayList;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.event.KeyEvent;
import java.util.Random;

public class Diver {

    private String playerName;
    private int idPlayer;
    private double xDiver;
    private double yDiver;
    private Level playerLevel;
    private String diverType;
    public static ArrayList<Diver> playerList = new ArrayList<>();
    private ArrayList<TreasureChest> chestTransported = new ArrayList<>();
    private ArrayList<TreasureChest> depositList = new ArrayList<>();
    private ArrayList<Integer> scoreList = new ArrayList<>();


    public Diver(String playerName, int idPlayer, double x, double y, Level playerLevel, String diverType) {
        this.playerName = playerName;
        this.idPlayer = idPlayer;
        this.xDiver = x;
        this.yDiver = y;
        this.playerLevel = playerLevel;
        this.diverType = diverType;
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

    public void setYDiver(double y) {
        this.yDiver = y;
    }

    public String getDiverType() {
        return diverType;
    }




    public static void game() {
        int phase = 1;
        while (phase <= 3) {
            StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
            StdDraw.filledRectangle(400, 725, 400, 25);
            StdDraw.show();
            int n3 = Cave.NList.get(3);
            Random rnd = new Random();
            int r = rnd.nextInt(2);
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.text(400, 760, Diver.playerList.get(r).getPlayerName() + " STARTS");
            StdDraw.show();

            while (Reserve.oxygen > 0) {
                if (phase == 1) {

                    //on détermine qui va jouer en premier
                    if (r == 0) {
                        playerList.get(0).whichPlayFunction(phase); //joueur 1 joue en premier
                        playerList.get(1).whichPlayFunction(phase);
                    }
                    if (r == 1) {
                        playerList.get(1).whichPlayFunction(phase); //joueur 2 joue en premier
                        playerList.get(0).whichPlayFunction(phase);
                    }
                } else {
                    if (sortPlayers() == 1) {
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.text(400, 760, Diver.playerList.get(0).getPlayerName() + " STARTS");
                        playerList.get(0).whichPlayFunction(phase); //joueur 1 joue en premier
                        playerList.get(1).whichPlayFunction(phase);

                    } else if (sortPlayers() == 2) {
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.text(400, 760, Diver.playerList.get(1).getPlayerName() + " STARTS");
                        playerList.get(1).whichPlayFunction(phase); //joueur 2 joue en premier
                        playerList.get(0).whichPlayFunction(phase);

                    } else { //les deux joueurs sont au même niveau, donc random
                        if (r == 0) {
                            playerList.get(0).whichPlayFunction(phase); //joueur 1 joue en premier
                            playerList.get(1).whichPlayFunction(phase);
                        }
                        if (r == 1) {
                            playerList.get(1).whichPlayFunction(phase); //joueur 2 joue en premier
                            playerList.get(0).whichPlayFunction(phase);
                        }
                    }

                }
            }

            if (Reserve.oxygen <= 0) {
                phase++;
                if (phase <= 3) {
                    //on fait tomber dans le dernier niveau de la cave3 les coffres que portaient les joueurs
                    playerList.get(0).looseChests();
                    playerList.get(1).looseChests();

                    //on initialise pour la phase suivante
                    deleteLevelWithoutChest(Main.cave1);
                    int newn1 = Main.cave1.getLevelList().size();
                    Cave.NList.set(1, newn1); //on modifie la NList

                    deleteLevelWithoutChest(Main.cave2);
                    int newn2 = Main.cave2.getLevelList().size();
                    Cave.NList.set(2, newn2);

                    deleteLevelWithoutChest(Main.cave3);
                    int newn3 = Main.cave3.getLevelList().size();
                    Cave.NList.set(3, newn3);

                    int N = newn1 + newn2 + newn3;
                    Reserve.oxygen = 2 * N; //nouvelle réserve
                    System.out.println("phase = " + phase);

                    Diver.playerList.get(0).getChestTransported().clear(); //on vide les listes de coffres transportés
                    Diver.playerList.get(1).getChestTransported().clear(); //on vide les listes de coffres transportés

                    //on affiche les niveaux ayant un coffre
                    Main.displayLevelsAndChests(Main.cave1);
                    Main.displayLevelsAndChests(Main.cave2);
                    Main.displayLevelsAndChests(Main.cave3);

                    //on replace les joueurs au niveau1 de la cave1
                    playerList.get(0).setPlayerLevel(Main.cave1.getLevelList().get(0));
                    playerList.get(1).setPlayerLevel(Main.cave1.getLevelList().get(0));
                    playerList.get(0).setYDiver(Main.cave1.getLevelList().get(0).getYLevel());
                    playerList.get(1).setYDiver(Main.cave1.getLevelList().get(0).getYLevel());
                    playerList.get(0).displayDiver(); //affichage des joueurs
                    playerList.get(1).displayDiver();
                }
            }
        }


        if (phase > 3) { //fin du jeu : on affiche les scores et on désigne le vainqueur
            displayScore();
            int score1 = Diver.playerList.get(0).countScore();
            int score2 = Diver.playerList.get(1).countScore();
            if (score1>score2) {
                System.out.println("Le gagnant est " + Diver.playerList.get(0).getPlayerName() + " !");
                StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
                StdDraw.filledRectangle(400, 725, 400, 25);
                StdDraw.setPenColor(StdDraw.WHITE);
                StdDraw.text(400, 760, "AND THE WINNER IS... " + Diver.playerList.get(0).getPlayerName() + " !");
                StdDraw.show();
            }
            else if (score1==score2) {
                System.out.println(Diver.playerList.get(0).getPlayerName() + " et " + Diver.playerList.get(1).getPlayerName() + " sont à égalité.");
                StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
                StdDraw.filledRectangle(400, 725, 400, 25);
                StdDraw.setPenColor(StdDraw.WHITE);
                StdDraw.text(400, 760, Diver.playerList.get(0).getPlayerName() + " AND " + Diver.playerList.get(1).getPlayerName() + " 's SCORES ARE EQUALS" + " !");
                StdDraw.show();
            }
            else {
                System.out.println("Le gagnant est " + Diver.playerList.get(1).getPlayerName() + " !");
                StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
                StdDraw.filledRectangle(400, 725, 400, 25);
                StdDraw.setPenColor(StdDraw.WHITE);
                StdDraw.text(400, 760, "AND THE WINNER IS... " + Diver.playerList.get(1).getPlayerName() + " !");
                StdDraw.show();
            }
        }
        StdDraw.show();
    }


    public void humanPlay(int phase) {
        //int n = Cave.NList.get(cave.getIdCave()); //nombre de niveaux dans la cave
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
            if (this.playerLevel == Main.cave0.getLevelList().get(0)) { //deposer le(s) coffre(s) dans le dépot
                System.out.println("Joueur " + this.getPlayerName() + " dépose ses coffres");
                validateChest();
                this.countScore();
                counter++;
                try {  //fait en sorte que l'action de déposer le coffre ne s'effectue qu'une seule fois
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(this.depositList);
            }
        }
    }


    public void IAPlay(int phase) {
        int counter = 0;
        while (counter == 0) {
            if (this.getChestTransported().size() == 0) { //si l'IA n'a pas de coffre, elle va en chercher un
                if (this.playerLevel.getChest() == null) {
                    this.goDown(Main.caveList.get(this.getPlayerLevel().getIdCaveLevel() - 1), this.getPlayerLevel());
                    Reserve.oxygen = Reserve.oxygenConsumption(this.getChestTransported().size(), Reserve.oxygen);
                } else {
                    this.takeChest(Main.caveList.get(this.getPlayerLevel().getIdCaveLevel() - 1), this.getPlayerLevel(), phase);
                    Reserve.oxygen = Reserve.oxygenConsumption(0, Reserve.oxygen);
                }
                counter++;
            } else if (this.getChestTransported().size() == 1) { //dès l'IA a un coffre, elle part le valider
                if (this.playerLevel == Main.cave1.getLevelList().get(0)) {
                    System.out.println("IA dépose son coffre");
                    validateChest();
                    System.out.println(this.depositList);
                } else {
                    this.goUp(Main.caveList.get(this.getPlayerLevel().getIdCaveLevel() - 1), this.getPlayerLevel());
                    Reserve.oxygen = Reserve.oxygenConsumption(this.getChestTransported().size(), Reserve.oxygen);
                }
                counter++;
            }
            try { //fait en sorte que l'action ne s'effectue qu'une seule fois
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }




   public void takeChest(Cave cave, Level playerLevel, int phase) {
        if (playerLevel.getChest() != null) {
            this.getChestTransported().add(cave.getLevelList().get(playerLevel.getIdLevel()-1).getChest());
            deleteChest(cave, playerLevel, cave.getLevelList().get(playerLevel.getIdLevel() - 1).getChest(), phase);
            playerLevel.setChest(null);
            System.out.println("Joueur " + this.getPlayerName()+ " : " + this.getChestTransported());
        } else if (playerLevel.getIdCaveLevel() == 3 && playerLevel.getIdLevel() == Cave.NList.get(3)) { //si on est dans le dernier niveau de la cave3
            if (phase!=1) {
                Random rnd = new Random();
                int min = 0;
                int max = Main.cave3.getLevelList().get(Cave.NList.get(3)).getChestList().size() - 1;
                int k = rnd.nextInt(max - min + 1) + min;
                this.getChestTransported().add(Main.cave3.getLevelList().get(Cave.NList.get(3) - 1).getChestList().get(k));
                playerLevel.getChestList().remove(k);
                deleteChest(cave, playerLevel, Main.cave3.getLevelList().get(Cave.NList.get(3) - 1).getChestList().get(k), phase);
            }
        }
        else {
            //on ajoute rien
        }
   }

   public void goDown(Cave cave, Level playerLevel) {
           this.deletePlayer(cave, playerLevel);
       int n = Cave.NList.get(cave.getIdCave());
           int idLevelDown = playerLevel.getIdLevel() + 1;

           if (idLevelDown <= n) {
               this.yDiver = cave.getLevelList().get(idLevelDown-1).getYLevel();
               this.setPlayerLevel(cave.getLevelList().get(idLevelDown-1));
           } else {
               int newIdCave = cave.getIdCave() + 1;
               if (newIdCave == 1) {
                   this.yDiver = Main.cave1.getLevelList().get(0).getYLevel();
                   this.setPlayerLevel(Main.cave1.getLevelList().get(0));
               }
               if (newIdCave == 2) {
                   this.yDiver = Main.cave2.getLevelList().get(0).getYLevel();
                   this.setPlayerLevel(Main.cave2.getLevelList().get(0));
               }
               if (newIdCave == 3) {
                   this.yDiver = Main.cave3.getLevelList().get(0).getYLevel();
                   this.setPlayerLevel(Main.cave3.getLevelList().get(0));
               }
               else {
                   // on est tout en bas, on ne fait donc rien
               }
           }
       StdDraw.picture(this.getXDiver(), this.getYDiver(), "Plongeur.png", 30, 30);
       StdDraw.show();
   }

   public void goUp(Cave cave, Level playerLevel) {
       this.deletePlayer(cave, playerLevel);
       int idLevelUp = playerLevel.getIdLevel() - 1;
       if (idLevelUp == 0) {
           if (cave.getIdCave() == 0) {
               //on ne fait rien
           } else if (cave.getIdCave() == 1) {
               //si on est en cave1, on monte au deposit
               this.yDiver = Main.cave0.getLevelList().get(0).getYLevel();
               this.setPlayerLevel(Main.cave0.getLevelList().get(0));
           } else {
               int newIdCave = cave.levelList.get(0).getIdCaveLevel() - 1;
               if (newIdCave == 1) {
                   int n1 = Cave.NList.get(1);
                   this.yDiver = Main.cave1.getLevelList().get(n1 - 1).getYLevel();
                   this.setPlayerLevel(Main.cave1.getLevelList().get(n1 - 1));
               }
               else if (newIdCave == 2) {
                   int n2 = Cave.NList.get(2);
                   this.yDiver = Main.cave2.getLevelList().get(n2 - 1).getYLevel();
                   this.setPlayerLevel(Main.cave2.getLevelList().get(n2 - 1));
               }
               else {
                   // on est tout en haut, on ne fait donc rien
               }
           }
       }
       else {
           this.yDiver = cave.getLevelList().get(idLevelUp-1).getYLevel();
           this.setPlayerLevel(cave.getLevelList().get(idLevelUp-1));
       }
       StdDraw.picture(this.getXDiver(), this.getYDiver(), "Plongeur.png", 30, 30);
       StdDraw.show();

   }

    public void validateChest() {
        for (int i = 0; i < this.chestTransported.size(); i++) {
            TreasureChest tmp = this.chestTransported.get(i);
            this.depositList.add(tmp);
        }
        this.getChestTransported().clear();
    }

    private static void deleteChest(Cave cave, Level level, TreasureChest chest, int phase) {
        if (cave.getIdCave() == 1) {
            int n = Cave.NList.get(1);
            double h = Main.cave1.getCaveHeight() / n;
            StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            StdDraw.filledRectangle(chest.getXChest(), (700 - (h / 2) - ((level.getIdLevel() - 1) * h)), 12, 12);
        }
        if (cave.getIdCave() == 2) {
            int n = Cave.NList.get(2);
            double h = Main.cave2.getCaveHeight() / n;
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.filledRectangle(chest.getXChest(), (375 - (h / 2) - ((level.getIdLevel() - 1) * h)), 12, 11.9);
        }
        if (cave.getIdCave() == 3) {
            int n = Cave.NList.get(3);
            double h = Main.cave3.getCaveHeight() / n;
            StdDraw.setPenColor(StdDraw.DARK_GRAY);
            if (level == cave.getLevelList().get(n - 1) && phase > 1) {
                StdDraw.filledRectangle(chest.getXChest(), (150 - (h / 2) - ((level.getIdLevel() - 1) * h)), 12, 12);
            } else {
                StdDraw.filledRectangle(chest.getXChest(), (150 - (h / 2) - ((level.getIdLevel() - 1) * h)), 12, 12);
            }
        }
        StdDraw.show();

    }

    private void deletePlayer(Cave cave, Level level) {
        if (cave.getIdCave() == 1) {
            int n = Cave.NList.get(1);
            double h = Main.cave1.getCaveHeight() / n;
            StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            StdDraw.filledRectangle(this.getXDiver(), (700 - (h / 2) - ((level.getIdLevel() - 1) * h)), 15, 10);
        }
        if (cave.getIdCave() == 2) {
            int n = Cave.NList.get(2);
            double h = Main.cave2.getCaveHeight() / n;
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.filledRectangle(this.getXDiver(), (375 - (h / 2) - ((level.getIdLevel() - 1) * h)), 15, 10);
        }
        if (cave.getIdCave() == 3) {
            int n = Cave.NList.get(3);
            double h = Main.cave3.getCaveHeight() / n;
            StdDraw.setPenColor(StdDraw.DARK_GRAY);
            StdDraw.filledRectangle(this.getXDiver(), (150 - (h / 2) - ((level.getIdLevel() - 1) * h)), 15, 10);
        }
        StdDraw.show();
    }

    public int countScore() {
        int score = 0;
        for (int i=0; i<this.depositList.size(); i++) {
            int nbTreasures = this.depositList.get(i).getTreasures();
            this.scoreList.add(nbTreasures);
        }
        for (int i = 0; i < this.scoreList.size(); i++) {
            score = score + this.scoreList.get(i);
        }
        return score;
    }

    public static void displayScore() {
        for (int i=0; i<Diver.playerList.size(); i++) {
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.text(70, 810 - i * 40, "Score : " + Integer.toString(Diver.playerList.get(i).countScore())); //score à afficher
        }
    }

    public static void deleteLevelWithoutChest(Cave cave) {
        // partie graphique
        if (cave.getIdCave() == 1) {
            StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            StdDraw.filledRectangle(400,Main.y1, 400, Main.cave1.getCaveHeight()/2);
        }
        if (cave.getIdCave() == 2) {
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.filledRectangle(400,Main.y2, 400, Main.cave2.getCaveHeight()/2);
        }
        if (cave.getIdCave() == 3) {
            double n = Cave.NList.get(3);
            double h = Main.cave3.getCaveHeight() / n;
            StdDraw.setPenColor(StdDraw.DARK_GRAY);
            StdDraw.filledRectangle(400,Main.y3, 400, (Main.cave3.getCaveHeight()-h-30)/2);
        }
        StdDraw.show();

        //partie ArrayList
        deleteEmptyLevels(cave);
    }

    public static void deleteEmptyLevels(Cave cave) {
        int n = Cave.NList.get(cave.getIdCave());
        int counter=0;
        while (counter != n - 1) {
            if (cave.getLevelList().size() > 1) { //voir si c'est ok'
                if (cave.getLevelList().get(0).getChest() == null) {
                    cave.getLevelList().remove(0);
                }
                counter++;
            } else {
                //on ne fait rien
            }
        }

        double newn = cave.getLevelList().size();
        double h = cave.getCaveHeight()/newn;
        int beginning =0;
        if (cave.getIdCave() ==1) {
            beginning = 700;
        }
        if (cave.getIdCave() == 2) {
            beginning = 375;
        }
        if (cave.getIdCave() == 3) {
            beginning = 150;
        }
        for (int i=0; i<newn;i++) {
            cave.getLevelList().get(i).setYLevel(beginning - (i*h) - (h/2));
            cave.getLevelList().get(i).setIdLevel(i + 1);
        }
    }

    public void displayDiver() {
        int k = this.getIdPlayer();
        StdDraw.picture(200 - 80 * (k - 1), Main.cave1.getLevelList().get(0).getYLevel(), "Plongeur.png", 30, 30);
        StdDraw.show();
    }

    public static int sortPlayers() {
        int t = 0;
        if (playerList.get(0).getYDiver() > playerList.get(1).getYDiver()) { //si joueur 1 est plus bas que joueur 2
            t = 1;
        } else if (playerList.get(0).getYDiver() < playerList.get(1).getYDiver()) { //si joueur 2 est plus bas que joueur 1
            t = 2;
        } else { //si les deux joueurs sont au même niveau : random
            //t reste à 0
        }
        return t;
    }


    public void whichPlayFunction(int phase) {
        if (this.getDiverType().compareTo("h") == 0) { //si le joueur est un humain
            humanPlay(phase);
        }
        if (this.getDiverType().compareTo("i") == 0) { //si le joueur est une IA
            IAPlay(phase);
        }
    }

    public void looseChests() {
        int n3 = Cave.NList.get(3);
        System.out.println(this.getChestTransported());
        int N = this.getChestTransported().size(); //nb de coffre transportes par le joueur 1
        System.out.println("size " + N);
        TreasureChest tmp;
        for (int i = 0; i < N; i++) {
            tmp = this.getChestTransported().get(i);
            System.out.println("tmp" + i + " : " + tmp);
            Main.cave3.getLevelList().get(n3 - 1).getChestList().add(tmp);
            System.out.println("chestList " + Main.cave3.getLevelList().get(n3 - 1).getChestList());
            System.out.println("apres avoir enlevé tmp" + this.getChestTransported());
            tmp.setXChest(750 - ((this.idPlayer - 1) * 200) - ((i + 1) * 50));
            StdDraw.picture(tmp.getXChest(), Main.cave3.getLevelList().get(n3 - 1).getYLevel(), "coffre aux trésors.png", 25, 25);
        }
        this.getChestTransported().clear();
        StdDraw.show();
    }
}
