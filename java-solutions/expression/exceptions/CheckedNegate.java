package expression.exceptions;

import expression.BinaryOperations;
import expression.Express;
import expression.Negate;

public class CheckedNegate extends Negate {
    public CheckedNegate(Express a) {
        super(a);
    }
    @Override
    public int func (int a)  {
        if (a == Integer.MIN_VALUE) {
            throw new OverflowException("-" + a);
        }
        return -a;
    }

}
