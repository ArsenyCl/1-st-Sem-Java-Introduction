package expression.exceptions;

import expression.Add;
import expression.Express;

public class CheckedAdd extends Add {
    public CheckedAdd(Express first, Express second) {
        super(first, second);
    }
    @Override
    public int func(int a, int b)  {
            if (a > 0 && b > 0 && Integer.MAX_VALUE - a < b || a < 0 && b < 0 && Integer.MIN_VALUE - a > b) {
                throw new OverflowException(a  + " + " + b);
            }
            return a + b;
    }
}
