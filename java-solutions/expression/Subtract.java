package expression;

public class Subtract extends BinaryOperations {
    public Subtract(Express first, Express second) {
        super(first, second);
        super.operation = "-";
    }
    @Override
    public int evaluate(int variable) {
        return super.first.evaluate(variable) - super.second.evaluate(variable);
    }
    @Override
    public int evaluate(int var1, int var2, int var3) {
        return super.first.evaluate(var1, var2, var3) - super.second.evaluate(var1, var2, var3);
    }
    @Override
    public double evaluate(double variable) {
        return super.first.evaluate(variable) - super.second.evaluate(variable);
    }
}
