package expression.exceptions;
import expression.Express;
import expression.Clear;

public class CheckedClear extends Clear {
    public CheckedClear(Express first, Express second) {
        super(first, second);
    }
    @Override
    public int func(int a, int b) {
//        if (b > 32 || b < 0 ) throw new InvalidInputException("Statement " + a + " clear " + b + " is illegal");
        return super.func(a, b);
    }
}