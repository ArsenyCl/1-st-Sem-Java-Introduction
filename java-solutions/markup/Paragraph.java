package markup;

import java.util.List;
import java.util.ArrayList;

public class Paragraph implements ACMarkup {
    private final List<ACMarkup> field;
    public Paragraph(List<ACMarkup> in) {
        field = new ArrayList(in);
    }
    public void toMarkdown(StringBuilder textBuilder) {
        for (ACMarkup each : field) {
            each.toMarkdown(textBuilder);
        }
    }
    public void toTex(StringBuilder textBuild) {
        for (ACMarkup each : field) {
            each.toTex(textBuild);
        }
    }
}
