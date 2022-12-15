package expression;

public class Clear extends BinaryOperations {
    public Clear(Express first, Express second) {
        super(first, second, "clear", true, false, ExpressOperation.getPriority("clear"));
    }

    @Override
    public int func(int a, int b) {
        return a & ~(1 << b);
    }
}
