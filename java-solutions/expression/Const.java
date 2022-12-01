package expression;
public class Const implements Express {
    private int valueInt;
    private double valueDouble;
    private final boolean intConst;
    public Const(int value) {
        this.intConst = true;
        this.valueInt = value;
    }
    public Const(double value) {
        this.intConst = false;
        this.valueDouble = value;
    }
    @Override
    public int evaluate(int variable) {
        return valueInt;
    }
    @Override
    public double evaluate(double variable) {
        return valueDouble;
    }
    @Override
    public int evaluate(int var1, int var2, int var3) {
        return valueInt;
    }
    @Override
    public String toString() {
        return  intConst ?  String.valueOf(valueInt) : String.valueOf(valueDouble);
    }
    @Override
    public boolean equals(Object o) {
        if (o == null ||  getClass() != o.getClass()) return false;
        Const that = (Const) o;
        if (this.intConst && that.intConst) {
            return this.valueInt == that.valueInt;
        } else {
            return this.valueDouble == that.valueDouble;
        }
    }
    public int hashCode() {
        return this.intConst ? valueInt : Double.hashCode(valueDouble);
    }
}
