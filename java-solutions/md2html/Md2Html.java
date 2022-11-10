package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Md2Html {
    private static int strongZv = -1;
    private static int strongPod = -1;
    private static int emZv = -1;
    private static int emPod = -1;
    private static int s = -1;
    private static int code = -1;
    private static void checkSymb(String in, ArrayList<String> list) {
        if (in.equals("**")) {
            if (strongZv > -1) {
                list.set(strongZv, "<strong>");
                list.set(list.size() - 1, "</strong>");
                strongZv = -1;
            } else {
                strongZv = list.size() - 1;
            }
        } else if (in.equals("__")) {
            if (strongPod > -1) {
                list.set(strongPod, "<strong>");
                list.set(list.size() - 1, "</strong>");
                strongPod = -1;
            } else {
                strongPod = list.size() - 1;
            }
        } else if (in.equals("*")) {
            if (emZv > -1) {
                list.set(emZv, "<em>");
                list.set(list.size() - 1, "</em>");
                emZv = -1;
            } else {
                emZv = list.size() - 1;
            }
        } else if (in.equals("_")) {
            if (emPod > -1) {
                list.set(emPod, "<em>");
                list.set(list.size() - 1, "</em>");
                emPod = -1;
            } else {
                emPod = list.size() - 1;
            }
        } else if(in.equals("--")) {
            if (s > -1) {
                list.set(s, "<s>");
                list.set(list.size()-1, "</s>");
                s = -1;
            } else {
                s = list.size()-1;
            }
        } else if(in.equals("`")) {
            if (code > -1) {
                list.set(code, "<code>");
                list.set(list.size()-1, "</code>");
                code = -1;
            } else {
                code = list.size()-1;
            }
        } else if (in.equals("<")) {
            list.set(list.size()-1, "&lt;");
        } else if (in.equals(">")) {
            list.set(list.size()-1, "&gt;");
        } else if (in.equals("&")) {
            list.set(list.size()-1, "&amp;");
        } else if(in.equals("\\*")) {
            list.set(list.size()-1, "*");
        } else if (in.equals("\\_")) {
            list.set(list.size() - 1, "_");
        }
    }
    public static void main(String[] args) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8))) {
            SpecialScanner in = new SpecialScanner(new File(args[0]), StandardCharsets.UTF_8);
            try {
                while(in.hasNext()) {
                    ArrayList<String> out = new ArrayList<>();
                    String head;
                    if (in.next().charAt(0) == '#' && in.next().charAt(in.next().length()-1) == ' ') {
                         head = "h" + (in.next().length()-1) + ">";
                         out.add("<"+head);
                    } else {
                        head = "p>";
                        out.add("<" + head);
                        if (in.next().charAt(0)=='\n') {
                            continue;
                        } else {
                            out.add(in.next());
                            checkSymb(in.next(), out);
                        }
                    }
                    while (in.hasNext()) {
                        if (in.skipLines) {
                            break;
                        } else {
                            out.add(in.next());
                            checkSymb(in.next(), out);
                        }

                    }
                    if (out.get(out.size()-1).charAt(0) == '\n') {
                        out.remove(out.size()-1);
                    }
                    out.add("</" + head);
                    for (String each: out) {
                        writer.write(each);
                    }
                    writer.newLine();
                    emZv = -1;
                    emPod = -1;
                    strongZv = -1;
                    strongPod = -1;
                    s = -1;
                    code = -1;
                }
            } finally {
                in.close();
            }

        } catch(IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
