import java.io.IOException;
import java.util.Arrays;

public class Reverse {
    private static String[][] multArray(String[][] a) {
        String[][] b = Arrays.copyOf(a, a.length * 2);
        return b;
    }

    private static String[] multStr(String[] a) {
        String[] b = Arrays.copyOf(a, a.length * 2);
        return b;
    }

    private static int[] multInt(int[] a) {
        int[] b = Arrays.copyOf(a, a.length * 2);
        return b;
    }

    public static void main(String[] args) {
        try {
            ACScanner text = new ACScanner(System.in);
            try {
                String[][] lines = new String[1][];
                int[] indexes = new int[1];
                int linesSize = 0;
                String[] digits = new String[1];
                int digitsSize = 0;
                while (text.hasNextDigit()) {
                    int n = text.hasFoundNewLines;
                    while (n > 0) {
                        if (linesSize == lines.length) {
                            indexes = multInt(indexes);
                            lines = multArray(lines);
                        }
                        indexes[linesSize] = digitsSize;
                        lines[linesSize] = digits;
                        linesSize++;
                        n--;
                        digits = new String[1];
                        digitsSize = 0;
                    }
                    if (digitsSize == digits.length) {
                        digits = multStr(digits);
                    }
                    digits[digitsSize] = text.nextDigit();
                    digitsSize++;
                }
                if (linesSize == lines.length) {
                    indexes = multInt(indexes);
                    lines = multArray(lines);
                }
                indexes[linesSize] = digitsSize;
                lines[linesSize] = digits;
                linesSize++;
                int n = text.hasFoundNewLines;
                while (n > 1) {
                    if (linesSize == lines.length) {
                        indexes = multInt(indexes);
                        lines = multArray(lines);
                    }
                    lines[linesSize] = new String[1];
                    indexes[linesSize] = 0;
                    linesSize++;
                    n--;
                }
                for (int i = linesSize - 1; i >= 0; i--) {
                    for (int j = indexes[i] - 1; j >= 0; j--) {
                        System.out.print(lines[i][j] + " ");
                    }
                    System.out.print('\n');
                }
            } finally {
                text.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}

