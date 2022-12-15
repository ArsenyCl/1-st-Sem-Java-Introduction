package md2html;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;


public class Md2Html {
    public static HashMap<String, ACPair> subs = new HashMap<String, ACPair>();
    public static HashMap<String, String> change = new HashMap<String, String>();

    public static void main(String[] args) {
        change.put("<", "&lt;");
        change.put(">", "&gt;");
        change.put("&", "&amp;");
        change.put("\\*", "*");
        change.put("\\_", "_;");
        change.put("\\++", "++");
        change.put("\\+", "+");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8))) {
            SpecialScanner in = new SpecialScanner(new File(args[0]), StandardCharsets.UTF_8);
            try {
                while (in.hasNext()) {
                    ACPair strongZv = new ACPair(-1, "strong>");
                    ACPair strongPod = new ACPair(-1, "strong>");
                    ACPair emZv = new ACPair(-1, "em>");
                    ACPair emPod = new ACPair(-1, "em>");
                    ACPair s = new ACPair(-1, "s>");
                    ACPair code = new ACPair(-1, "code>");
                    ACPair u = new ACPair(-1, "u>");
                    subs.put("**", strongZv);
                    subs.put("__", strongPod);
                    subs.put("*", emZv);
                    subs.put("`", code);
                    subs.put("_", emPod);
                    subs.put("--", s);
                    subs.put("++", u);
                    ArrayList<String> out = new ArrayList<>();
                    String head;
                    if (in.next().charAt(0) == '#' && in.next().charAt(in.next().length() - 1) == ' ') {
                        head = "h" + (in.next().length() - 1) + ">";
                        out.add("<" + head);
                    } else {
                        head = "p>";
                        out.add("<" + head);
                        if (in.next().charAt(0) == '\n') {
                            continue;
                        } else {
                            out.add(change.getOrDefault(in.next(), in.next()));
                            if (subs.containsKey(in.next())) {
                                int index = subs.get(in.next()).intValue;
                                String sub = subs.get(in.next()).strValue;
                                if (index > -1) {
                                    out.set(index, "<" + sub);
                                    out.set(out.size() - 1, "</" + sub);
                                    subs.get(in.next()).setIntValue(-1);
                                } else {
                                    subs.get(in.next()).setIntValue(out.size() - 1);
                                }
                            }
                        }
                    }
                    while (in.hasNext()) {
                        if (in.skipLines) {
                            break;
                        } else {
                            out.add(change.getOrDefault(in.next(), in.next()));
                            if (subs.containsKey(in.next())) {
                                int index = subs.get(in.next()).intValue;
                                String sub = subs.get(in.next()).strValue;
                                if (index > -1) {
                                    out.set(index, "<" + sub);
                                    out.set(out.size() - 1, "</" + sub);
                                    subs.get(in.next()).setIntValue(-1);
                                } else {
                                    subs.get(in.next()).setIntValue(out.size() - 1);
                                }
                            }
                        }
                    }
                    if (out.get(out.size() - 1).charAt(0) == '\n') {
                        out.remove(out.size() - 1);
                    }
                    out.add("</" + head);
                    for (String each : out) {
                        writer.write(each);
                    }
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println(e.getLocalizedMessage());
            } finally {
                in.close();
            }

        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public static class ACPair {
        public int intValue;
        public String strValue;

        public ACPair(int intIn, String strIn) {
            intValue = intIn;
            strValue = strIn;
        }

        public void setIntValue(int in) {
            intValue = in;
        }
    }
}
