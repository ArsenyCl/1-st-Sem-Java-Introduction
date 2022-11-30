package TicTacToe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
             while (true) {
                 try {
                     System.out.println("Select game mode: ");
                     System.out.println("1 - Single game");
                     System.out.println("2 - Tournament");
                     String modeCheck = in.nextLine();
                     if (modeCheck.equals("1") || modeCheck.equals("2")) {
                         new Tournament(Integer.parseInt(modeCheck));
                         break;
                     }
                        System.out.println("Invalid input! Try again");
                        System.out.println();
                    } catch (RuntimeException e) {
                        System.out.println(e.getLocalizedMessage());
                        System.out.println();
                    }
             }
        }
}
