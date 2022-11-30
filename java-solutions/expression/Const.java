package expression;

import java.util.Objects;

public class Const implements Expression {
    private int value;
    public Const(int value) {
        this.value = value;
    }
    @Override
    public int evaluate(int variable) {
        return value;
    }
    @Override
    public String toString() {
        return String.valueOf(value);
    }
    @Override
    public boolean equals(Object o) {
        if (o == null ||  getClass() != o.getClass()) return false;
        Const that = (Const) o;
        return this.value == that.value;
    }
    public int hashCode() {
        return value;
    }
}
