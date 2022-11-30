package expression;

public class Divide extends BinaryOperations{
    public Divide(Expression first, Expression second) {
        super(first, second);
        super.operation = "/";
    }
    @Override
    public int evaluate(int variable) {
        return super.first.evaluate(variable) / super.second.evaluate(variable);
    }
}
