package markup;

import java.util.List;
import java.util.ArrayList;
public class Strong implements ACMarkup {
    private final List<ACMarkup> field;
    public Strong(List<ACMarkup> in) {
        field = new ArrayList(in);
    }
    public void toMarkdown(StringBuilder textbuilder) {
        textbuilder.append("__");
        for (ACMarkup each : field) {
            each.toMarkdown(textbuilder);
        }
        textbuilder.append("__");
    }
}
