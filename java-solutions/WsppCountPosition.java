import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WsppCountPosition {
    private static List<Map.Entry<String, IntArray>> sortWords(HashMap<String, IntArray> words) {
        return words.entrySet().stream().sorted(new Comparator<Map.Entry<String, IntArray>>() {
            @Override
            public int compare(Map.Entry<String, IntArray> secondEntry, Map.Entry<String, IntArray> firstEntry) {
                if (firstEntry.getValue().get(0) == secondEntry.getValue().get(0)) {
                    if ((firstEntry.getValue().get(2) == secondEntry.getValue().get(2))) {
                        return secondEntry.getValue().get(1) - firstEntry.getValue().get(1);
                    }
                    return secondEntry.getValue().get(2) - firstEntry.getValue().get(2);
                }
                return secondEntry.getValue().get(0) - firstEntry.getValue().get(0);
            }
        }).toList();
    }
    public static void main(String[] args) {
        try {
            ACScanner text = new ACScanner(new File(args[0]), StandardCharsets.UTF_8);
            try {
                HashMap<String, IntArray> words = new HashMap<>();
                int index = 1;
                int line = 1;
                while (text.hasNextWord()) {
                    if (text.hasFoundNewLines > 0) {
                        line+= text.hasFoundNewLines;
                        index = 1;
                    }
                    String word = text.nextWord().toLowerCase();
                    if (words.containsKey(word)) {
                        int count = words.get(word).get(0);
                        words.get(word).append(index);
                        words.get(word).append(line);
                        words.get(text.nextWord().toLowerCase()).set(0, count + 1);
                    } else {
                        words.put(word, new IntArray(2));
                        words.get(word).append(1);
                        words.get(word).append(index);
                        words.get(word).append(line);
                        }
                    index++;
                    }
                BufferedWriter writer = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8));
                try {
                    List<Map.Entry<String, IntArray>> sortedWords = sortWords(words);
                    for (Map.Entry<String, IntArray> pair: sortedWords) {
                        writer.write(pair.getKey());
                        writer.write(" " + pair.getValue().get(0));
                        for (int i = 2; i < pair.getValue().size(); i+=2) {
                            writer.write(" " + pair.getValue().get(i) + ":" + pair.getValue().get(i-1));
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
