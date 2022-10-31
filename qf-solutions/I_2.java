import java.util.Scanner;

public class TASK_I {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Scanner line = new Scanner(in.nextLine());
        int numberOfObelisks = line.nextInt();
        int xR = 0;
        int xL = (int)Math.pow(10, 8) + 1;
        int yR = 0;
        int yL = (int)Math.pow(10, 8) + 1;
        for (int i = 0; i < numberOfObelisks; i++) {
            line = new Scanner(in.nextLine());
            int xI = line.nextInt();
            int yI = line.nextInt();
            int hI = line.nextInt();
            xR = Math.max(xR, xI + hI);
            xL =  Math.min(xL, xI - hI);
            yR =  Math.max(yR, yI + hI);
            yL =  Math.min(yL, yI - hI);
        }
        int h = (int)Math.ceil((float) Math.max(xR - xL, yR - yL) / 2 );
        int x = (xL + xR) / 2;
        int y = (yL + yR) / 2;
        if ((x != (int)Math.ceil((float)(xL+xR) / 2)) || (y != (int)Math.ceil((float)(yL+yR) / 2))) {
            h+=1;
        }
        System.out.println(x + " " + y + " " + h);
    }
}
