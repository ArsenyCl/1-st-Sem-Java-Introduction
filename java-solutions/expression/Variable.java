package expression;

import java.util.Objects;

public class Variable implements Express {
    private final String valueString;
    public Variable(String valueString) {
        this.valueString = valueString;
    }

    @Override
    public int evaluate(int variable) {
        return variable;
    }
    @Override
    public boolean getAssoc() {
        return true;
    }
    @Override
    public boolean getRightAssoc() {
        return true;
    }
    @Override
    public int evaluate(int var1, int var2, int var3) {
        if (valueString.equals("x")) {
            return var1;
        } else if (valueString.equals("y")) {
            return var2;
        } else {
            return var3;
        }
    }
    @Override
    public String toString() {
        return valueString;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null ||  getClass() != o.getClass()) return false;
        Variable that = (Variable) o;
        return Objects.equals(this.valueString, that.valueString);
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public int hashCode() {
        return valueString.hashCode();
    }
}
