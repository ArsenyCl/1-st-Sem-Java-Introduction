package markup;

import java.util.List;
import java.util.ArrayList;
public class Strikeout implements ACMarkup {
    private final List<ACMarkup> field;
    public Strikeout(List<ACMarkup> in) {
        field = new ArrayList(in);
    }
    public void toMarkdown(StringBuilder textbuilder) {
        textbuilder.append("~");
        for (ACMarkup each : field) {
            each.toMarkdown(textbuilder);
        }
        textbuilder.append("~");
    }
}
