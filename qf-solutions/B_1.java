import java.util.Scanner;

public class TASK_B {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        double minMod = 10;
        int foundMin = 0;
        for (int i = 1; i < (Math.pow(2, 31) - 1)  / 50000; i++) {
            if (i % (2  * Math.PI ) <  minMod) {
                minMod = i % (2  * Math.PI );
                foundMin = i;
            }
        }
        for (int i = -25000; i + 25000 < n; i++) {
            System.out.println(i * foundMin);
        }

    }
}
