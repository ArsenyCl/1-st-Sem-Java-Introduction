package markup;

public class Text implements ACMarkup {

    private final String field;

    public Text(String in) {
        field = in;
    }

    public void toMarkdown(StringBuilder textBuilder) {
        textBuilder.append(field);
    }
    public void toTex(StringBuilder textBuild) {
        textBuild.append(field);
    }
}
