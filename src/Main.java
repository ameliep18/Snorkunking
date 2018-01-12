
import edu.princeton.cs.introcs.StdDraw;
import java.awt.*;
import java.util.Scanner;


public class Main {

    public static Cave cave1;
    public static Cave cave2;
    public static Cave cave3;

    public static void main(String[] args) {

        //initialisation

        cave1 = new Cave(1, 325);
        cave2 = new Cave(2,225);
        cave3 = new Cave(3,150);



        displayCanvas();
        displayCave();
        displayLevelAndChests(cave1);
        displayLevelAndChests(cave2);
        displayLevelAndChests(cave3);
        displayDiver();
        //Diver.diverList.get(0).takeChest(cave1, cave1.getLevelList().get(0));


    }

    private static double y1 = 375 + 325/2; //coordonnée y du centre du rectangle représentant la cave1
    private static double y2 = 150 + 225/2; //coordonnée y du centre du rectangle représentant la cave2
    private static double y3 = 150/2; //coordonnée y du centre du rectangle représentant la cave3


    private static void displayCanvas() {
        StdDraw.setCanvasSize(800, 900);
        StdDraw.setXscale(0, 800);
        StdDraw.setYscale(0, 900);
        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
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

    private static void displayCave() {
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.filledRectangle(400, y1, 400, 325/2);
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.filledRectangle(400, y2, 400, 225/2);
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.filledRectangle(400, y3, 400, 150/2);
        StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE); // depot = level0, là où l'on dépose les coffres
        StdDraw.filledRectangle(400, 725, 400, 25);
        StdDraw.show();
    }

    private static void displayLevelAndChests(Cave cave) {
        int n=0;
        double h = 0;
        //System.out.println(n);
        if (cave.getIdCave() == 1) {
            n = cave.getNList().get(0);
            h = cave1.getCaveHeight() / n;
        }
        if (cave.getIdCave() == 2) {
            n = cave.getNList().get(1);
            h = cave2.getCaveHeight() / n;
        }
        if (cave.getIdCave() == 3) {
            n = cave.getNList().get(2);
            h = cave3.getCaveHeight() / n;
        }
        StdDraw.setPenColor(StdDraw.WHITE);
        for (int i=0; i<n; i++) {
            StdDraw.rectangle(cave.getLevelList().get(i).getXLevel(), cave.getLevelList().get(i).getYLevel(), 400, h/2);
            StdDraw.picture(750, cave.getLevelList().get(i).getYLevel(), "coffre aux trésors.jpg", 25, 25);
        }
        StdDraw.show();
        System.out.println(h);
    }


    private static void displayDiver() {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many players ?");
        int nbPlayers = sc.nextInt();
        System.out.println("How many IA ?");
        int nbIA = sc.nextInt();
        int divers = nbPlayers + nbIA;

        if (divers <= 2) {
            Font font2 = new Font("Arial", Font.BOLD, 15);
            StdDraw.setFont(font2);
            for (int i = 1; i <= nbPlayers; i++) {
                System.out.println("Nom du joueur numéro " + i + " :");
                Diver diver = new Diver(sc.next(),200- ((i - 1) * 70), 725);
                Diver.diverList.add(diver);
                StdDraw.text(75, 825 - (i * 25), "Player" + i + " : " + diver.getPlayerName());
                StdDraw.picture(diver.getXDiver(), diver.getYDiver(), "Plongeur.jpg", 35, 35); //affiche le plongeur sur la ligne de départ
                StdDraw.show();
                //System.out.println(diver.getPlayerName());
            }
            //System.out.println(Diver.diverList);
            for (int i = 1; i <= nbIA; i++) {
                String nameIA = "IA " + Integer.toString(i);
                Diver IA = new Diver(nameIA, 300 - ((i - 1) * 70), 725);
                Diver.IAList.add(IA);
                StdDraw.picture(IA.getXDiver(), IA.getYDiver(), "IA.jpg", 35, 35); //affiche le plongeur sur la ligne de départ
                StdDraw.show();
            }
        }
        else {
            System.out.println("Please do not enter much than 2 players (diver or IA)");
            System.out.println("Start again");
        }
    }

    private static void deleteChest(Cave cave, Level level) {
        if (cave.getIdCave() == 1) {
            int n = cave.getNList().get(0);
            double h = cave1.getCaveHeight() / n;
            StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            StdDraw.filledRectangle(750,(700 - (h/2)- ((level.getIdLevel()-1) * h)), 14, 14);
        }
        if (cave.getIdCave() == 2) {
            int n = cave.getNList().get(1);
            double h = cave2.getCaveHeight() / n;
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.filledRectangle(750,(375 - (h/2)- ((level.getIdLevel()-1) * h)), 14, 14);
        }
        if (cave.getIdCave() == 3) {
            int n = cave.getNList().get(2);
            double h = cave3.getCaveHeight() / n;
            StdDraw.setPenColor(StdDraw.DARK_GRAY);
            StdDraw.filledRectangle(750,(150 - (h/2)- ((level.getIdLevel()-1) * h)), 14, 14);
        }
        StdDraw.show();

    }

}








