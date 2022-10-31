import java.util.Scanner;

public class TASK_A {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] task = new int[3];
        int iterator = 0;
        while (iterator < 3) {
            task[iterator] = in.nextInt();
            iterator++;
        }
        int out = 2 * (int) Math.ceil(( (float) (task[2]-task[1]) / (task[1] - task[0]))) + 1;
        System.out.println(out);
    }
}
