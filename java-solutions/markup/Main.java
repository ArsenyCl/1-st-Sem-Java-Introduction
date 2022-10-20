package markup;

import java.util.List;
import java.lang.StringBuilder;

public class Main {
    public static void main(String[] args) {
        StringBuilder textBuilder = new StringBuilder();
        Paragraph paragraph = new Paragraph(List.of(
                new Strong(List.of(
                        new Text("1"),
                        new Strikeout(List.of(
                                new Text("2"),
                                new Emphasis(List.of(
                                        new Text("3"),
                                        new Text("4")
                                )),
                                new Text("5")
                        )),
                        new Text("6")
                ))
        ));
        paragraph.toMarkdown(textBuilder);
        System.out.println(textBuilder.toString());
    }
}