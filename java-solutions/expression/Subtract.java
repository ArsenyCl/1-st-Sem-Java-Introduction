package expression;

public class Subtract extends BinaryOperations {
    public Subtract(Express first, Express second) {
        super(first, second, "-", false, true,ExpressOperation.getPriority("-"));
    }
    @Override
    public int func(int a, int b) {
        return a - b;
    }
}
