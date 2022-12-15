package markup;

import java.util.ArrayList;
import java.util.List;

public class Strikeout extends Markdown {
    public Strikeout(List<ACMarkup> in) {
        super(in);
        super.mark = "~";
        super.texMark = "\\textst{";
        field = new ArrayList(in);
    }
}
