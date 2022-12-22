package expression.exceptions;

import expression.Divide;
import expression.Express;

public class CheckedDivide extends Divide {
    public CheckedDivide(Express first, Express second)  {
        super(first, second);
    }
    @Override
    public int func(int a, int b) {
            if (b == 0) {
               throw new DivisionByZeroException("Division by zero" + a + " / " + b);
            } else if(a == Integer.MIN_VALUE && b == -1) {
                throw new OverflowException(a  + " / " + b);
            }
            return a / b;
    }
}
