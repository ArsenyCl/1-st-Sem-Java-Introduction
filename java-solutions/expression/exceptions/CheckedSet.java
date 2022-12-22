package expression.exceptions;

import expression.Express;
import expression.Set;

public class CheckedSet extends Set {
    public CheckedSet(Express first, Express second) {
        super(first, second);
    }
    @Override
    public int func(int a, int b) {
//        if (b > 32 || b < 0 ) throw new InvalidInputException("Statement " + a + " set " + b + " is illegal");
        return super.func(a, b);
    }
}
