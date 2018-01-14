
import edu.princeton.cs.introcs.StdDraw;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static Cave cave1;
    public static Cave cave2;
    public static Cave cave3;
    public static Level deposit = new Level(1,0, null, 400,725); //création du depot = level0, là où l'on dépose les coffres
    public static ArrayList<Cave> caveList = new ArrayList<>();

    public static void main(String[] args) {

        //initialisation

        cave1 = new Cave(1, 325);
        cave2 = new Cave(2,225);
        cave3 = new Cave(3,150);
        Reserve reserve = new Reserve(cave1, cave2,cave3);
        caveList.add(cave1);
        caveList.add(cave2);
        caveList.add(cave3);



        displayCanvas();
        displayCave();
        displayLevelAndChests(cave1);
        displayLevelAndChests(cave2);
        displayLevelAndChests(cave3);
        displayReserve();
        displayDiver();
        Diver.game(1);



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
        StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE); // depot = level0, là où l'on dépose les coffres
        StdDraw.filledRectangle(400, 725, 400, 25);
        StdDraw.show();
    }

    private static void displayLevelAndChests(Cave cave) {
        int n=0;
        double h = 0;
        //System.out.println(n);
        if (cave.getIdCave() == 1) {
            n = Cave.NList.get(0);
            h = cave1.getCaveHeight() / n;
        }
        if (cave.getIdCave() == 2) {
            n = Cave.NList.get(1);
            h = cave2.getCaveHeight() / n;
        }
        if (cave.getIdCave() == 3) {
            n = Cave.NList.get(2);
            h = cave3.getCaveHeight() / n;
        }
        StdDraw.setPenColor(StdDraw.WHITE);
        for (int i=0; i<n; i++) {
            StdDraw.rectangle(cave.getLevelList().get(i+1).getXLevel(), cave.getLevelList().get(i+1).getYLevel(), 400, h/2);
            StdDraw.picture(750, cave.getLevelList().get(i+1).getYLevel(), "coffre aux trésors.png", 25, 25);
        }
        StdDraw.show();
        //System.out.println(h);
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
                Diver diver = new Diver(sc.next(),i,200- ((i - 1) * 70), 725,deposit);
                Diver.playerList.add(diver);
                StdDraw.setPenColor(StdDraw.WHITE);
                StdDraw.text(70, 835 - (i * 28), "Player" + i + " : " + diver.getPlayerName());
                StdDraw.picture(diver.getXDiver(), diver.getYDiver(), "Plongeur.jpg", 30, 30); //affiche le plongeur sur la ligne de départ
                StdDraw.show();
            }
            //System.out.println(Diver.diverList);
            for (int i = 1; i <= nbIA; i++) {
                String nameIA = "IA " + Integer.toString(i);
                Diver IA = new Diver(nameIA,i, 300 - ((i - 1) * 70), 725,deposit);
                Diver.playerList.add(IA);
                StdDraw.text(200, 835 - (i * 28), "Player" + i + " : " + IA.getPlayerName());
                StdDraw.picture(IA.getXDiver(), IA.getYDiver(), "IA.jpg", 30, 30); //affiche le plongeur sur la ligne de départ
                StdDraw.show();
            }
        }
        else {
            System.out.println("Please do not enter much than 2 players (diver or IA)");
            System.out.println("Start again");
        }
    }

    public static void displayReserve() {
        int n = Reserve.oxygen;
        StdDraw.filledRectangle(690,790, 2*n, 10);
        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
        for (int i=0; i<n; i++)
            StdDraw.filledRectangle((690-n) + ((2*i)+1),790,2,9);
        StdDraw.show();

    }






}








