package expression;
public class Const implements Express {
    private final int valueInt;
    public Const(int value) {
        this.valueInt = value;
    }
    @Override
    public int getPriority() {
        return 2;
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
    public int evaluate(int variable) {
        return valueInt;
    }
    @Override
    public int evaluate(int var1, int var2, int var3) {
        return valueInt;
    }
    @Override
    public String toString() {
        return String.valueOf(valueInt);
    }
    @Override
    public boolean equals(Object o) {
        if (o == null ||  getClass() != o.getClass()) return false;
        Const that = (Const) o;
        return this.valueInt == that.valueInt;
    }
    public int hashCode() {
        return this.valueInt;
    }
}
