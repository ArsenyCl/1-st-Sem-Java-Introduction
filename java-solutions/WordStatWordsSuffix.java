import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;

public class WordStatWordsSuffix {
    private static AC_Pair<String, Integer>[] multPair(AC_Pair<String, Integer>[] a) {
        Map.Entry<Integer, String> test = Map.entry(5, "");
        AC_Pair<String, Integer>[] b = Arrays.copyOf(a, a.length * 2);
        return b;
    }
    private static String[] multstr(String[] a) {
        String[] b = Arrays.copyOf(a, a.length * 2);
        return b;
    }
    private static boolean compare( AC_Pair<String, Integer> a,  AC_Pair<String, Integer> b) {
        int i = 0;
        boolean equal = false;
        boolean returning  = false;
        while (i  < a.getFirst().length() && i < b.getFirst().length()) {
            if (a.getFirst().charAt(i) < b.getFirst().charAt(i)) {
                returning = true;
                equal = true;
                break;
            } else if (a.getFirst().charAt(i) > b.getFirst().charAt(i)) {
                returning = false;
                equal = true;
                break;
            } else {
                i++;
            }
        }
        if (!equal) {
            if (a.getFirst().length() < b.getFirst().length()) {
                returning = true;
            } else {
                returning = false;
            }
        }
        return returning;
    }
    private static AC_Pair<String,Integer>[] pairStrSort(AC_Pair<String,Integer>[] a, int b) {
        int i = b;
        boolean check = true;
        AC_Pair<String, Integer> temporary;
        while (i > 0 && check) {
            check = compare(a[i], a[i-1]);
            if (check) {
                temporary = a[i];
                a[i] = a[i-1];
                a[i-1] = temporary;
            }
            i--;
        }
        return a;
    }
    private static AC_Pair<String,Integer>[] pairIntSort(AC_Pair<String,Integer>[] a, int b) {
        int i = b;
        AC_Pair<String, Integer> temporary;
        while(i > 0 && a[i].getSecond() > a[i-1].getSecond()) {
            temporary = a[i];
            a[i] = a[i-1];
            a[i-1] = temporary;
            i--;
        }
        return a;
    }

    public static void main(String[] args) {
        try {
            ACScanner scanner = new ACScanner(args[0], StandardCharsets.UTF_8);
            try {
                AC_Pair<String, Integer>[] words = new AC_Pair[1];
                int wordsSize = 0;
                String textLine;
                while ((textLine = scanner.scanNewLine()) != null)  {
                    ACScanner line = new ACScanner(textLine);
                    String word;
                    while ((word = line.nextWord()) != null)  {
                        int n = word.length();
                        if (word.length() > 3) {
                            word = word.substring(n-3, n);
                        }
                        boolean consists = false;
                        for (int j = 0; j < wordsSize; j++) {
                            if (words[j].getFirst().equals(word.toLowerCase())) {
                                consists = true;
                                words[j].setSecond(words[j].getSecond() + 1);
                                break;
                            }
                        }
                        if (!consists) {
                            if (wordsSize == words.length) {
                                words = multPair(words);
                            }
                            words[wordsSize] = new AC_Pair<>(word.toLowerCase(), 1);
                            words = pairStrSort(words, wordsSize);
                            wordsSize++;
                        }
                    }
                }
                BufferedWriter writer = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8));
                try {
                    for (int m = 0; m < wordsSize; m++) {
                        writer.write(words[m].getFirst() + " " + words[m].getSecond());
                        writer.newLine();
                    }
                } finally {
                    writer.close();
                }
            } finally {
                scanner.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
