package expression;

import java.util.Objects;

public class Variable implements Expression {
    private final String valueString;
    public Variable(String valueString) {
        this.valueString = valueString;
    }

    @Override
    public int evaluate(int variavle) {
        return variavle;
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
    public int hashCode() {
        return valueString.hashCode();
    }
}
