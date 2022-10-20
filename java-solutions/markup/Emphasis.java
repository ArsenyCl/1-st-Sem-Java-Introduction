package markup;

import java.util.List;
import java.util.ArrayList;

public class Emphasis implements ACMarkup {

    private final List<ACMarkup> field;

    public Emphasis(List<ACMarkup> in) {
        field = new ArrayList(in);
    }
    public void toMarkdown(StringBuilder textBuilder) {
        textBuilder.append("*");
        for (ACMarkup each : field) {
            each.toMarkdown(textBuilder);
        }
        textBuilder.append("*");
    }
}
