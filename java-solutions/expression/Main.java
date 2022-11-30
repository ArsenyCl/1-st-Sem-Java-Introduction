package expression;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Subtract(new Const(2), new Variable("x")).equals(new Subtract(new Const(2), new Variable("x"))));
    }
}
