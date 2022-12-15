package markup;

import java.util.ArrayList;
import java.util.List;

public class Strong extends Markdown {
    public Strong(List<ACMarkup> in) {
        super(in);
        super.mark = "__";
        super.texMark = "\\textbf{";
        field = new ArrayList(in);
    }


}
