package expression;

public class Subtract extends BinaryOperations {
    public Subtract(Expression first, Expression second) {
        super(first, second);
        super.operation = "-";
    }
    @Override
    public int evaluate(int variable) {
        return super.first.evaluate(variable) - super.second.evaluate(variable);
    }
}
