import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.File;
import java.io.BufferedWriter;

public class Wspp {
    public static void main(String[] args) {
        try {
            ACScanner text = new ACScanner(new File(args[0]), StandardCharsets.UTF_8);
            try {
                HashMap<String, IntArray> words = new HashMap<>();
                int index = 1;
                ArrayList<String> order = new ArrayList<>();
                while (text.hasNextWord()) {
                    String word = text.nextWord().toLowerCase();
                    words.putIfAbsent(word, new IntArray(1));
                    words.get(word).append(index);
                    if (words.get(word).size() == 1) {
                        order.add(word);
                    }
                    index++;
                }
                BufferedWriter writer = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8));
                try {
                    for (String word: order) {
                        writer.write(word + " " + words.get(word).size());
                        for (int i = 0; i < words.get(word).size(); i++) {
                            writer.write(" " + words.get(word).get(i));
                        }
                        writer.newLine();
                    }
                } finally {
                    writer.close();
                }
            } finally {
                text.close();
            }

        } catch(IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
