package expression;

import java.util.Objects;

public abstract class BinaryOperations implements Express {
    protected Express first;
    protected Express second;
    protected String operation;
    protected boolean assoc;
    protected boolean rightAssoc;
    protected int priority;

    public BinaryOperations(Express first, Express second, String operation, boolean assoc, boolean rightAssoc, int priority) {
        this.first = first;
        this.second = second;
        this.operation = operation;
        this.assoc = assoc;
        this.rightAssoc = rightAssoc;
        this.priority = priority;
    }

    public int func(int a, int b) {
        return 0;
    }

    @Override
    public int evaluate(int variable) {
        return this.func(first.evaluate(variable, 0, 0), second.evaluate(variable, 0, 0));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return this.func(first.evaluate(x, y, z), second.evaluate(x, y, z));
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
        StringBuilder ans = new StringBuilder();
        if (this.getPriority() <= first.getPriority()) {
            ans.append(first.toMiniString());
        } else {
            ans.append("(").append(first.toMiniString()).append(")");
        }
        ans.append(" ").append(this.operation).append(" ");
        if (this.getPriority() < second.getPriority() ||
                (this.assoc && second.getRightAssoc() && this.getPriority() <= second.getPriority())) {
            ans.append(second.toMiniString());
        } else {
            ans.append("(").append(second.toMiniString()).append(")");
        }
        return ans.toString();
    }
}
