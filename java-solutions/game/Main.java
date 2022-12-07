package game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
                 try {
                     Scanner in = new Scanner(System.in);
                     System.out.println("Select game mode: ");
                     System.out.println("1 - Single game");
                     System.out.println("2 - Tournament");
                     int modeCheck = in.nextInt();
                     new Tournament(modeCheck);
                 } catch (RuntimeException e) {
                        System.out.println(e.getLocalizedMessage());
                        System.out.println();
                 }
    }
}

