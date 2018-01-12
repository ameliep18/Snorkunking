
import edu.princeton.cs.introcs.StdDraw;
import java.awt.*;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        //initialisation

        Cave cave1 = new Cave(1);
        Cave cave2 = new Cave(2);
        Cave cave3 = new Cave(3);


        displayCanvas();
        displayCave();
        displayLevelAndChests(cave1);
        displayLevelAndChests(cave2);
        displayLevelAndChests(cave3);
        displayDiver();

    }

    public static int x = 400; //coordonnée x du centre du rectangle représentant les caves
    public static double L1 = 325 / 2; //demie largeur cave1
    public static double L2 = 225 / 2; //demie largeur cave2
    public static double L3 = 150 / 2; //demie largeur cave3
    public static double y1 = 375 + L1; //coordonnée y du centre du rectangle représentant la cave1
    public static double y2 = 2 * L3 + L2; //coordonnée y du centre du rectangle représentant la cave2
    public static double y3 = L3; //coordonnée y du centre du rectangle représentant la cave3


    public static void displayCanvas() {
        StdDraw.setCanvasSize(800, 950);
        StdDraw.setXscale(0, 800);
        StdDraw.setYscale(0, 900);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledRectangle(0, 0, 800, 900);
        StdDraw.setPenColor(StdDraw.WHITE);
        Font font1 = new Font("Arial", Font.BOLD, 30);
        StdDraw.setFont(font1);
        StdDraw.text(400, 870, "SNORKUNKING");
        Font font3 = new Font("Arial", Font.BOLD, 18);
        StdDraw.setFont(font3);
        StdDraw.text(700, 800, "OXYGEN");
        StdDraw.show();
    }

    public static void displayCave() {
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.filledRectangle(x, y1, x, L1);
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.filledRectangle(x, y2, x, L2);
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.filledRectangle(x, y3, x, L3);
        StdDraw.show();
    }

    public static void displayLevelAndChests(Cave cave) {
        int n = cave.nbLevels();
        System.out.println(n);
        if (cave.getIdCave() == 1) {
            double heightPerLevel = (2 * L1) / n;
            StdDraw.setPenColor(StdDraw.WHITE);
            for (int i = 0; i < n - 1; i++) {
                StdDraw.rectangle(x, ((700 - heightPerLevel) - (i * heightPerLevel)), x, heightPerLevel);
                StdDraw.picture(750, ((700 - (heightPerLevel / 2)) - (i * heightPerLevel)), "coffre aux trésors.jpg", 25, 25);
                StdDraw.show();
            }
            StdDraw.picture(750, ((700 - (heightPerLevel / 2)) - ((n - 1) * heightPerLevel)), "coffre aux trésors.jpg", 25, 25);
            StdDraw.show();
        }
        if (cave.getIdCave() == 2) {
            double heightPerLevel = (2 * L2) / n;
            StdDraw.setPenColor(StdDraw.WHITE);
            for (int i = 0; i < n - 1; i++) {
                StdDraw.rectangle(x, ((375 - heightPerLevel) - (i * heightPerLevel)), x, heightPerLevel);
                StdDraw.picture(750, ((375 - (heightPerLevel / 2)) - (i * heightPerLevel)), "coffre aux trésors.jpg", 25, 25);
                StdDraw.show();
            }
            StdDraw.picture(750, ((375 - (heightPerLevel / 2)) - ((n - 1) * heightPerLevel)), "coffre aux trésors.jpg", 25, 25);
            StdDraw.show();
        }
        if (cave.getIdCave() == 3) {
            double heightPerLevel = (2 * L3) / n;
            StdDraw.setPenColor(StdDraw.WHITE);
            for (int i = 0; i < n - 1; i++) {
                StdDraw.rectangle(x, ((150 - heightPerLevel) - (i * heightPerLevel)), x, heightPerLevel);
                StdDraw.picture(750, ((150 - (heightPerLevel / 2)) - (i * heightPerLevel)), "coffre aux trésors.jpg", 25, 25);
                StdDraw.show();
            }
            StdDraw.picture(750, ((150 - (heightPerLevel / 2)) - ((n - 1) * heightPerLevel)), "coffre aux trésors.jpg", 25, 25);
            StdDraw.show();
        }
    }

    public static void displayDiver() {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many players ?");
        int nbPlayers = sc.nextInt();
        System.out.println("How many IA ?");
        int nbIA = sc.nextInt();
        int divers = nbPlayers + nbIA;

        if (divers <= 4) {
            Font font2 = new Font("Arial", Font.BOLD, 15);
            StdDraw.setFont(font2);
            for (int i = 1; i <= nbPlayers; i++) {
                System.out.println("Nom du joueur numéro " + i + " :");
                Diver diver = new Diver(sc.next());
                Diver.diverList.add(diver);
                StdDraw.text(75, 825 - (i * 25), "Player" + i + " : " + diver.getPlayerName());
                StdDraw.picture(100 + ((i - 1) * 70), 718, "Plongeur.jpg", 35, 35); //affiche le plongeur sur la ligne de départ
                StdDraw.show();
                System.out.println(diver.getPlayerName());
            }
            System.out.println(Diver.diverList);
            for (int i = 1; i <= nbIA; i++) {
                String nameIA = "IA " + Integer.toString(i);
                Diver IA = new Diver(nameIA);
                Diver.IAList.add(IA);
                StdDraw.picture(300 + ((i - 1) * 70), 718, "IA.jpg", 35, 35); //affiche le plongeur sur la ligne de départ
                StdDraw.show();
            }
        } else {
            System.out.println("Please do not enter much than 4 players (diver or IA)");
            System.out.println("Start again");
        }
    }

}








