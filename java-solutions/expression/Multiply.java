package expression;

public class Multiply extends BinaryOperations {
    public Multiply(Expression first, Expression second) {
        super(first, second);
        super.operation = "*";
    }
    @Override
    public int evaluate(int variable) {
        return super.first.evaluate(variable) * super.second.evaluate(variable);
    }
}
