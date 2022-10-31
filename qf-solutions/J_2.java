import java.util.Scanner;

public class TASK_H {
    private static int modMinus(int a, int b, int mod) {
        return (((a - b) % mod) + mod) % mod;
    }
    public static void main(String[] args) {
        int mod = 10;
        Scanner text = new Scanner(System.in);
        int n = text.nextInt();
        int[][] graphic = new int[n][n];
        for (int i = 0; i < n; i++) {
            String line = text.next();
            for (int j = 0; j < n; j++) {
                graphic[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (graphic[i][j] > 0) {
                    for (int k = j + 1; k < n; k++) {
                        graphic[i][k] = modMinus(graphic[i][k], graphic[j][k], mod);
                    }
                }
            }
        }
        for (int[] eachArr: graphic) {
            for(int each: eachArr) {
                System.out.print(each);
            }
            System.out.println();
        }
    }
}

