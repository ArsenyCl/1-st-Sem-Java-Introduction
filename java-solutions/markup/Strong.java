package markup;

import java.util.List;
import java.util.ArrayList;
public class Strong extends Markdown {
    public Strong(List<ACMarkup> in) {
        super(in);
        super.mark = "__";
        super.texMark = "\\textbf{";
        field = new ArrayList(in);
    }


}
