package expression.exceptions;

import expression.Express;
import expression.Multiply;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(Express first, Express second)   {
        super(first, second);
    }
    @Override
    public int func(int a, int b)  {
        if (a > b) {
            int c = a;
            a = b;
            b = c;
        }
        if (a == 0) return 0;
        int c = a * b;
        if (c / a != b || a == Integer.MIN_VALUE && b == -1) {
            throw new OverflowException(a + " * " + b);
        } else {
            return a * b;
        }
    }
}
