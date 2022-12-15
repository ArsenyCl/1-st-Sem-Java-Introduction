package expression;

public class Add extends BinaryOperations {
    public Add(Express first, Express second) {
        super(first, second, "+", true, true, ExpressOperation.getPriority("+"));
    }

    @Override
    public int func(int a, int b) {
        return a + b;
    }
}
