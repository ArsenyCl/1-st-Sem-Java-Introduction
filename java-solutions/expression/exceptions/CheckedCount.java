package expression.exceptions;

import expression.Count;
import expression.Express;

public class CheckedCount extends Count {
    public CheckedCount(Express a) {
        super(a);
    }
    @Override
    public int func(int a) {
        return super.func(a);
    }
}
