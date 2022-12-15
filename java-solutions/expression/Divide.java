package expression;

public class Divide extends BinaryOperations{
    public Divide(Express first, Express second) {
        super(first, second, "/", false, false,ExpressOperation.getPriority("/"));
    }
    @Override
    public int func(int a, int b) {
        return a / b;
    }

}
