package markup;

import java.util.ArrayList;
import java.util.List;

public class Emphasis extends Markdown {
    public Emphasis(List<ACMarkup> in) {
        super(in);
        super.mark = "*";
        super.texMark = "\\emph{";
        field = new ArrayList(in);
    }
}
