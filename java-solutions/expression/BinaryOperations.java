package expression;

import java.util.Objects;

public abstract class BinaryOperations implements Expression {
    Expression first;
    Expression second;
    String operation;
    public BinaryOperations(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }
    @Override
    public String toString() {
        return "(" + first.toString() +
                " " + operation + " " +
                second.toString() + ")";
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || this.hashCode() != o.hashCode() || getClass() != o.getClass()) return false;
        BinaryOperations that = (BinaryOperations) o;
        return Objects.equals(first, that.first) && Objects.equals(second, that.second);
    }
    @Override
    public int hashCode() {
        return (((operation.hashCode() * 13) + first.hashCode() * 29) + second.hashCode() * 43);
    }
    public static  String antiCopyPasteFirst(Expression expression) {
        if (!(expression instanceof BinaryOperations) || expression instanceof Multiply || expression instanceof Divide) {
            return expression.toMiniString();
        } else {
            return "(" + expression.toMiniString() + ")";
        }
    }
    public static  String antiCopyPasteSecond(Expression expression, BinaryOperations operation) {
        if (!(expression instanceof BinaryOperations)  || (expression instanceof Multiply && !(operation instanceof Divide))) {
            return expression.toMiniString();
        } else {
            return "(" + expression.toMiniString() + ")";
        }
    }
    @Override
    public String toMiniString() {
        if (this instanceof Multiply) {
            return antiCopyPasteFirst(first) +
                    " " + operation + " " +
                    antiCopyPasteSecond(second, this);
        }
        if (this instanceof Divide) {
            return  antiCopyPasteFirst(first) +
                    " " + operation + " " +
                    antiCopyPasteSecond(second, this);
        }
        if (this instanceof Subtract && (second instanceof Add || second instanceof Subtract)) {
            return first.toMiniString() +
                    " " + operation + " (" +
                    second.toMiniString() + ")";
        }
        return  first.toMiniString() +
                " " + operation + " " +
                second.toMiniString();
    }
}
