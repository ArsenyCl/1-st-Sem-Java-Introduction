package expression;

public class Multiply extends BinaryOperations {
    public Multiply(Express first, Express second) {
        super(first, second, "*", true, true, 1);
    }
    @Override
    public int func(int a, int b) {
        return a * b;
    }
}
