package expression.exceptions;

import expression.Express;
import expression.Subtract;

public class CheckedSubtract extends Subtract {
    public CheckedSubtract(Express first, Express second) {
        super(first, second);
    }
    @Override
    public int func(int a, int b)  {
            if (a >= 0 && b <= 0 && Integer.MAX_VALUE + b < a || a < 0 && b > 0 && Integer.MIN_VALUE + a < b) {
                throw new OverflowException("a - b");
            } else {
                return a - b;
            }
    }
}
