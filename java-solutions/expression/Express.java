package expression;

public interface Express extends Expression, TripleExpression {
    int getPriority();

    boolean getAssoc();

    boolean getRightAssoc();
}
