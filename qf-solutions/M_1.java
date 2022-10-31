import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class TASK_M {
    private static int answer(int[] array, int arraySize) {
        int out = 0;
        HashMap<Integer, Integer> digitMap = new HashMap();
        for (int j = arraySize-2; j > 0; j--) {
            digitMap.put(array[j+1], digitMap.getOrDefault(array[j+1], 0)+1);
            for (int i = 0; i < j; i++) {
                out += digitMap.getOrDefault(2 * array[j] - array[i], 0);
            }
        }
        return out;
    }
    private static int[] dynamic(int[] array) {
        array = Arrays.copyOf(array, array.length * 2);
        return array;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader text = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(text.readLine());
        int[] outArray = new int[n];
        for (int i = 0; i < n; i++) {
            int newN = Integer.parseInt(text.readLine());
            Scanner line = new Scanner(text.readLine());
            int[] digitArray = new int[100];
            int arraySize = 0;
            while (line.hasNextInt()) {
                if (arraySize == digitArray.length) {
                    digitArray =  dynamic(digitArray);
                }
                digitArray[arraySize] = line.nextInt();
                arraySize++;
            }
            line.close();
            outArray[i] = answer(digitArray, arraySize);
        }
        text.close();
        for (int answer: outArray) {
            System.out.println(answer);
        }
    }
}
