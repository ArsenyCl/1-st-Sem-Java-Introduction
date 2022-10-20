package markup;

import java.util.ArrayList;
import java.util.List;

public abstract class Markdown implements ACMarkup {
    protected String mark;
    protected String texMark;
    protected List<ACMarkup> field;

    public Markdown(List<ACMarkup> in) {
        field = new ArrayList(in);
    }
    @Override
    public void toMarkdown(StringBuilder textBuilder) {
        textBuilder.append(mark);
        for (ACMarkup each : field) {
            each.toMarkdown(textBuilder);
        }
        textBuilder.append(mark);
    }
    @Override
    public void toTex(StringBuilder textBuilder){
        textBuilder.append(texMark);
            for (ACMarkup each : field) {
                each.toTex(textBuilder);
            }
        textBuilder.append("}");
    }
}
