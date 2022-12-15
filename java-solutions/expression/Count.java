package expression;

public class Count extends UnaryOperations {
    public Count(Express value) {
        super(value, "count", true, true, ExpressOperation.getPriority("count "));
    }

    @Override
    public int func(int a) {
        long max = (long) Math.pow(2, 31);
        int counter = 0;
        for (long i = 1; i < max - 1; i *= 2) {
            counter = (a & i) > 0 ? counter + 1 : counter;
        }
        return a < 0 ? counter + 1 : counter;
    }
}
