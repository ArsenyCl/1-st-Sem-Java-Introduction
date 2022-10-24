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
                if (firstEntry.getValue().size() == secondEntry.getValue().size()) {
                    if ((firstEntry.getValue().get(0) == secondEntry.getValue().get(0))) {
                        return secondEntry.getValue().get(1) - firstEntry.getValue().get(1);
                    }
                    return secondEntry.getValue().get(0) - firstEntry.getValue().get(0);
                }
                return secondEntry.getValue().size() - firstEntry.getValue().size();
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
                    words.putIfAbsent(word, new IntArray(2));
                    words.get(word).append(line);
                    words.get(word).append(index);
                    index++;
                    }
                BufferedWriter writer = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8));
                try {
                    List<Map.Entry<String, IntArray>> sortedWords = sortWords(words);
                    for (Map.Entry<String, IntArray> pair: sortedWords) {
                        writer.write(pair.getKey());
                        writer.write(" " + pair.getValue().size()/2);
                        for (int i = 0; i < pair.getValue().size(); i+=2) {
                            writer.write(" " + pair.getValue().get(i) + ":" + pair.getValue().get(i+1));
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
