package markup;

import java.util.List;
import java.util.ArrayList;

public class Emphasis extends Markdown {
    public Emphasis(List<ACMarkup> in) {
        super(in);
        super.mark = "*";
        super.texMark = "\\emph{";
        field = new ArrayList(in);
    }
}
