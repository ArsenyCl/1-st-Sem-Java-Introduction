package expression;

import java.util.Objects;

public abstract class UnaryOperations implements Express {
    protected Express first;
    protected String operation;
    protected boolean assoc;
    protected boolean rightAssoc;
    protected int  priority;
    public UnaryOperations(Express first, String operation, boolean assoc, boolean rightAssoc, int priority) {
        this.first = first;
        this.operation = operation;
        this.assoc = assoc;
        this.rightAssoc = rightAssoc;
        this.priority = priority;
    }
    public int func(int a) {
        return 0;
    }
    @Override
    public int evaluate(int variable) {
        return this.func(first.evaluate(variable, 0, 0));
    }
    @Override
    public int evaluate(int x, int y, int z) {
        return this.func(first.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return operation + "(" + first.toString() + ")";
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || this.hashCode() != o.hashCode() || getClass() != o.getClass()) return false;
        UnaryOperations that = (UnaryOperations) o;
        return Objects.equals(first, that.first);
    }
    @Override
    public int hashCode() {
        return (((operation.hashCode() * 23) + first.hashCode() * 43) + 97);
    }
    @Override
    public int getPriority() {
        return priority;
    }
    @Override
    public boolean getAssoc() {
        return assoc;
    }
    @Override
    public boolean getRightAssoc() {
        return rightAssoc;
    }
    @Override
    public String toMiniString() {
        if (first instanceof BinaryOperations) {
            return operation + "(" + first.toMiniString() + ")";
        } else {
            return operation + " " +first.toMiniString();
        }
    }
}
