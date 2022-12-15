package expression;

public class Set extends BinaryOperations {
    public Set(Express first, Express second) {
        super(first, second, "set", true, false, ExpressOperation.getPriority("set"));
    }

    @Override
    public int func(int a, int b) {
        return a | (1 << b);
    }
}
