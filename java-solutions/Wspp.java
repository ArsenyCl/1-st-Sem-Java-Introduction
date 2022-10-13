import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
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
                    if (words.containsKey(word)) {
                        int count = words.get(word).get(0);
                        words.get(word).append(index);
                        words.get(text.nextWord().toLowerCase()).set(0, count + 1);
                    } else {
                        words.put(word, new IntArray(2));
                        words.get(word).append(1);
                        words.get(word).append(index);
                        order.add(word);
                    }
                    index++;
                }
                BufferedWriter writer = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8));
                try {
                    for (String word: order) {
                        writer.write(word);
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
