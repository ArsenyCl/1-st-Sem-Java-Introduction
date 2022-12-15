package expression;

public class UnaryMinus extends UnaryOperations {
    public UnaryMinus(Express value) {
        super(value, "-", true, true, ExpressOperation.getPriority("- "));
    }
    @Override
    public int func(int a) {
        return -a;
    }
}
