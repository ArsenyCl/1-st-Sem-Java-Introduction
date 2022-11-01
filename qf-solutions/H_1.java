import java.util.Arrays;
import java.util.Scanner;

public class TASK_H {
    public static void main(String[] args) {
        Scanner text = new Scanner(System.in);
        int n = text.nextInt();
        int[] array = new int[n];
        array[0] = text.nextInt();
        int max = array[0];
        for (int i = 1; i < n; i++) {
            int next = text.nextInt();
            array[i] = array[i - 1] + next;
            max = Math.max(max, next);
        }
        int taskCount = text.nextInt();
        int[] tasks = new int[taskCount];
        for (int i = 0; i < taskCount; i++) {
            tasks[i] = text.nextInt();
        }
        for (int task: tasks) {
            if (max > task) {
                System.out.println("Impossible");
            } else {
                int count = 0;
                int found = 0;
                while(found + task < array[n - 1] ) {
                    int index = Arrays.binarySearch(array, found + task);
                    if (index < 0) {
                        index = - index - 2;
                    }
                    found = array[index];
                    count++;
                }
                System.out.println(count+1);
            }
        }
    }
}