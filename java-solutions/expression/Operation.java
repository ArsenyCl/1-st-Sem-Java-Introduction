package expression;

public class Operation {
    private final String type;
    private final boolean associative;
    private final boolean rightAssociative;

    protected Operation(String type, boolean associative, boolean rightAssociative) {
        this.associative = associative;
        this.rightAssociative = rightAssociative;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public boolean isAssociative() {
        return associative;
    }

    public boolean isRightAssociative() {
        return rightAssociative;
    }
}
