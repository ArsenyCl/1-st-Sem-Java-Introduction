import java.util.Scanner;
import java.util.Arrays;

public class ReverseTranspose {
    private static String[][] multiArray(String[][] a) {
        String[][] b = Arrays.copyOf(a, a.length * 2);
        return b;
    }

    private static int[] multiplyArray(int[] a) {
        int[] b = Arrays.copyOf(a, a.length * 2);
        return b;
    }
    private static String[] multStr(String[] a) {
        String[] b = Arrays.copyOf(a, a.length * 2);
        return b;
    }

    public static void main(String[] args) throws Exception {
        Scanner text = new Scanner(System.in);
        String nextStr;
        String[][] lines = new String[1][];
        int[] indexes = new int[1];
        int linessize = 0;
        int maxsize = 0;
        while (text.hasNextLine()) {
            String textLine = text.nextLine();
            if (linessize == lines.length) {
                lines = multiArray(lines);
                indexes = multiplyArray(indexes);
            }
            String[] digits = new String[1];
            int digitssize = 0;
            int index = 0;
            ACScanner line = new ACScanner(textLine);
            while (line.hasNextDigit()) {
                if (digitssize == digits.length) {
                    digits = multStr(digits);
                }
                digits[digitssize] = line.nextDigit();
                index++;
                digitssize++;
            }
            if (maxsize < digitssize) {
                maxsize = digitssize;
            }
            indexes[linessize] = index;
            lines[linessize] = digits;
            linessize++;
        }
        for (int i = 0; i < maxsize; i++) {
            for (int j = 0; j < linessize; j++) {
                if (i < indexes[j]) {
                    System.out.print(lines[j][i] + " ");
                }
            }
            System.out.println();

        }
    }
}
