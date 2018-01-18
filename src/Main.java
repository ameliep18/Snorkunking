
import edu.princeton.cs.introcs.StdDraw;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class Main {

    public static Cave cave0;
    public static Cave cave1;
    public static Cave cave2;
    public static Cave cave3;
    public static int reserveInit;
    public static ArrayList<Cave> caveList = new ArrayList<>();


    public static void main(String[] args) {
        gameLaunch();
    }

    public static double y1 = 375 + 325/2; //coordonnée y du centre du rectangle représentant la cave1
    public static double y2 = 150 + 225/2; //coordonnée y du centre du rectangle représentant la cave2
    public static double y3 = 150 / 2; //coordonnée y du centre du rectangle représentant la cave3


    private static void displayMenu() {
        StdDraw.setCanvasSize(800, 900);
        StdDraw.setXscale(0, 800);
        StdDraw.setYscale(0, 900);
        StdDraw.picture(400, 450, "menu.jpg", 800, 900);
        StdDraw.show();
    }


    private static void displayCanvas() {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
        StdDraw.filledRectangle(0, 0, 800, 900);
        StdDraw.setPenColor(StdDraw.WHITE);
        Font font1 = new Font("Arial", Font.BOLD, 30);
        StdDraw.setFont(font1);
        StdDraw.text(400, 870, "SNORKUNKING");
        Font font3 = new Font("Arial", Font.BOLD, 18);
        StdDraw.setFont(font3);
        StdDraw.text(700, 820, "OXYGEN");
        StdDraw.show();
    }

    private static void displayCave() {
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.filledRectangle(400, y1, 400, 325/2);
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.filledRectangle(400, y2, 400, 225/2);
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.filledRectangle(400, y3, 400, 150/2);
        StdDraw.show();
    }

    public static void displayLevelsAndChests(Cave cave) {
        int n=0;
        double h = 0;
        if (cave == cave0) {
            n = Cave.NList.get(0);
            h = cave0.getCaveHeight() / n;
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.rectangle(400, cave.getLevelList().get(0).getYLevel(), 400, h / 2);
            StdDraw.show();
        } else {
            if (cave == cave1) {
                n = Cave.NList.get(1);
                h = cave1.getCaveHeight() / n;

            }
            if (cave == cave2) {
                n = Cave.NList.get(2);
                h = cave2.getCaveHeight() / n;
            }
            if (cave == cave3) {
                n = Cave.NList.get(3);
                h = cave3.getCaveHeight() / n;
            }
            for (int i = 0; i < n; i++) {
                StdDraw.rectangle(400, cave.getLevelList().get(i).getYLevel(), 400, h / 2);
                StdDraw.picture(750, cave.getLevelList().get(i).getYLevel(), "coffre aux trésors.png", 25, 25);
            }
        }
        StdDraw.show();
    }

    private static void askForDivers() {
        int k = 1;
        while (k != 3) {
            if (StdDraw.isKeyPressed(KeyEvent.VK_H)) {
                try { //fait en sorte que l'appui sur le H ne s'effectue qu'une seule fois
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Diver diver = new Diver("Human " + Integer.toString(k), k, 120 + 80 * (k - 1), cave0.getLevelList().get(0).getYLevel(), cave0.getLevelList().get(0), "h");
                Diver.playerList.add(diver);
                k++;
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_I)) {
                try { //fait en sorte que l'appui sur le I ne s'effectue qu'une seule fois
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String nameIA = "IA " + Integer.toString(k);
                Diver IA = new Diver(nameIA, k, 120 + 80 * (k - 1), cave0.getLevelList().get(0).getYLevel(), cave0.getLevelList().get(0), "i");
                Diver.playerList.add(IA);
                k++;
            }
        }
    }


    private static void displayDiverInit() {
        StdDraw.setPenColor(StdDraw.WHITE);
        Font font2 = new Font("Arial", Font.BOLD, 15);
        StdDraw.setFont(font2);
        for (int i = 0; i < 2; i++) {
            if (Diver.playerList.get(i).getDiverType() == "h") {
                StdDraw.text(70, 830 - (i * 40), "Player" + Integer.toString(i + 1) + " : " + Diver.playerList.get(i).getPlayerName());
                StdDraw.picture(Diver.playerList.get(i).getXDiver(), Diver.playerList.get(i).getYDiver(), "Plongeur.png", 30, 30); //affiche le plongeur sur la ligne de départ
            } else if (Diver.playerList.get(i).getDiverType() == "i") {
                StdDraw.text(70, 830 - (i * 40), "Player" + Integer.toString(i + 1) + " : " + Diver.playerList.get(i).getPlayerName());
                StdDraw.picture(Diver.playerList.get(i).getXDiver(), Diver.playerList.get(i).getYDiver(), "Plongeur.png", 30, 30);
            }
        }
        StdDraw.show();

    }

    public static void displayReserve(int reserveInit, int oxygen) {
        double xOxygenL = (double) 600 + (((double) (reserveInit - oxygen) / (double) reserveInit) * 100.0); //partie gauche
        double xOxygenR = (double) 700 + (((double) (reserveInit - oxygen) / (double) reserveInit) * 100.0); //partie droite
        if (xOxygenL <= 700 && xOxygenR <= 800) {
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.filledRectangle(xOxygenL, 790, xOxygenL - 600, 10);
            StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
            StdDraw.filledRectangle(xOxygenR, 790, 800 - xOxygenR, 10);
            StdDraw.show();
        }
        else{
            StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
            StdDraw.filledRectangle(700, 790, 100, 10);
        }
    }

    public static void gameLaunch() {
        //initialisation
        cave0 = new Cave(0, 50);
        cave1 = new Cave(1, 325);
        cave2 = new Cave(2, 225);
        cave3 = new Cave(3, 150);
        caveList.add(cave1);
        caveList.add(cave2);
        caveList.add(cave3);
        reserveInit = Diver.oxygenReserve();
        //affichage
        displayMenu();
        askForDivers();
        displayCanvas();
        displayCave();
        displayLevelsAndChests(cave0);
        displayLevelsAndChests(cave1);
        displayLevelsAndChests(cave2);
        displayLevelsAndChests(cave3);
        displayReserve(reserveInit, reserveInit);
        displayDiverInit();
        //jeu
        Diver.game();
        //replay
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        Font font3 = new Font("Arial", Font.BOLD, 20);
        StdDraw.setFont(font3);
        StdDraw.text(400, 730, "Press 'R' if you want to replay");
        StdDraw.show();
        while (true) {
            if (StdDraw.isKeyPressed(KeyEvent.VK_R)) {
                StdDraw.clear();
                gameLaunch();
            }
        }
    }

}








