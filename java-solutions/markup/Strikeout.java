package markup;

import java.util.List;
import java.util.ArrayList;
public class Strikeout extends Markdown {
    public Strikeout(List<ACMarkup> in) {
        super(in);
        super.mark = "~";
        super.texMark = "\\textst{";
        field = new ArrayList(in);
    }
}
