
import edu.princeton.cs.introcs.StdDraw;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static Cave cave1;
    public static Cave cave2;
    public static Cave cave3;
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
        displayLevelsAndChests(cave1);
        displayLevelsAndChests(cave2);
        displayLevelsAndChests(cave3);
        displayReserve();
        displayDiver();
        Diver.game();




    }

    public static double y1 = 375 + 325/2; //coordonnée y du centre du rectangle représentant la cave1
    public static double y2 = 150 + 225/2; //coordonnée y du centre du rectangle représentant la cave2
    public  static double y3 = 150/2; //coordonnée y du centre du rectangle représentant la cave3


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

    public static void displayLevelsAndChests(Cave cave) {
        int n=0;
        double h = 0;
        if (cave.getIdCave() == 1) {
            n = Cave.NList.get(0);
            h = cave1.getCaveHeight() / n;
        }
        if (cave.getIdCave() == 2) {
            n = Cave.NList.get(1);
            System.out.println(n);
            h = cave2.getCaveHeight() / n;
        }
        if (cave.getIdCave() == 3) {
            n = Cave.NList.get(2);
            System.out.println(n);
            h = cave3.getCaveHeight() / n;
        }
        StdDraw.setPenColor(StdDraw.WHITE);
        for (int i=0; i<n; i++) {
            StdDraw.rectangle(400, cave.getLevelList().get(i).getYLevel(), 400, h/2);
            StdDraw.picture(750, cave.getLevelList().get(i).getYLevel(), "coffre aux trésors.png", 25, 25);
        }
        StdDraw.show();
    }


    private static void displayDiver() {
        int k=1;
        do {
            Scanner sc1 = new Scanner(System.in);
            System.out.println("Player " + k + " : Human or IA ?");
            System.out.println("Press 'h' for Human or 'i' for IA");
            if (sc1.next().compareTo("h") == 0) {
                System.out.println("Enter the name of the player " + k + " :");
                Diver diver = new Diver(sc1.next(), k, 200 - 80*(k-1), cave1.getLevelList().get(0).getYLevel(), cave1.getLevelList().get(0));
                Diver.playerList.add(diver);
                StdDraw.setPenColor(StdDraw.WHITE);
                Font font2 = new Font("Arial",Font.BOLD,15);
                StdDraw.setFont(font2);
                StdDraw.text(70, 830 - (k-1)*40, "Player" + k + " : " + diver.getPlayerName());
                StdDraw.picture(diver.getXDiver(), diver.getYDiver(), "Plongeur.jpg", 30, 30); //affiche le plongeur sur la ligne de départ
                k++;
            }
            else if (sc1.next().compareTo("i") == 0) {
                String nameIA = "IA " + Integer.toString(k);
                Diver IA = new Diver(nameIA, k, 200 - 80*(k-1) , cave1.getLevelList().get(0).getYLevel(), cave1.getLevelList().get(0));
                Diver.playerList.add(IA);
                StdDraw.text(70, 830 - (k-1)*40, "Player" + k + " : " + IA.getPlayerName());
                StdDraw.picture(IA.getXDiver(), IA.getYDiver(), "IA.jpg", 30, 30);
                k++;
            }
            else {
                System.out.println("Please push one of the key asked");
                displayDiver();
            }
        } while (k==2);
        StdDraw.show();

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








