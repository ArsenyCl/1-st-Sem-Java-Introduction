import java.util.Arrays;
import java.io.IOException;
public class ReverseAbc {
    private static String[][] multiArray(String[][] a) {
        String[][] b = Arrays.copyOf(a, a.length * 2);
        return b;
    }
    private static String[] multiplyArray(String[] a) {
        String[] b = Arrays.copyOf(a, a.length * 2);
        return b;
    }
    private static int[] multInt(int[] a) {
        int[] b = Arrays.copyOf(a, a.length * 2);
        return b;
    }

    private static String abcToInt(String a) {
        StringBuilder digit = new StringBuilder(0);
        for (int i = 0; i < a.length(); i++) {
            if (((int) a.charAt(i) - 97 >= 0 && (int) a.charAt(i) - 97 < 10)) {
                digit.append((int) a.charAt(i) - 97);
            } else {
                digit.append(a.charAt(i));
            }
        }
        return digit.toString();
    }

    public static void main (String[] args) throws Exception  {
        String nextStr;
        ACScanner text = new ACScanner(System.in);
        String[][] lines = new String[1][];
        int[] indexes;
        indexes = new int[1];
        int linessize = 0;
        String textLine;
        while ((textLine = text.scanNewLine()) != null) {
            if (linessize == lines.length) {
                lines = multiArray(lines);
                indexes = multInt(indexes);
            }
            String[] digits = new String[1];
            int digitssize = 0;
            int index = 0;
            ACScanner line = new ACScanner(textLine);
            while ((nextStr = line.next()) != null) {
                if (digitssize == digits.length) {
                    digits = multiplyArray(digits);
                }
                digits[digitssize] = nextStr;
                index++;
                digitssize++;
            }
            indexes[linessize] = index;
            lines[linessize] = digits;
            linessize++;
        }
        for (int i = linessize-1; i >= 0; i--) {
            for (int j = indexes[i]-1; j >= 0; j--) {
                System.out.print(lines[i][j] + " ");
            }
            System.out.println();
        }
    }
}


