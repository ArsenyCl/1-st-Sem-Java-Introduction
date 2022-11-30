package expression;

public class Add extends BinaryOperations {
    public Add(Expression first, Expression second) {
        super(first, second);
        super.operation = "+";
    }
    @Override
    public int evaluate(int variable) {
        return super.first.evaluate(variable) + super.second.evaluate(variable);
    }
}
