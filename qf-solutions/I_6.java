import java.util.Scanner;

public class TASK_I {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Scanner line = new Scanner(in.nextLine());
        int numberOfObelisks = line.nextInt();
        float xR = Float.MIN_VALUE;
        float xL = Float.MAX_VALUE;
        float yR = Float.MIN_VALUE;
        float yL = Float.MAX_VALUE;
        for (int i = 0; i < numberOfObelisks; i++) {
            line = new Scanner(in.nextLine());
            float xI = line.nextInt();
            float yI = line.nextInt();
            float hI = line.nextInt();
            xR = Math.max(xR, xI + hI);
            xL =  Math.min(xL, xI - hI);
            yR =  Math.max(yR, yI + hI);
            yL =  Math.min(yL, yI - hI);
        }
        long h = (long)Math.ceil(Math.max(xR - xL, yR - yL) / 2 );
        long x = (long)(xL + xR) / 2;
        long y = (long)(yL + yR) / 2;
        System.out.println(x + " " + y + " " + h);
    }
}
