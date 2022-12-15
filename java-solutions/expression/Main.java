package expression;



public class Main {
    public static void main(String[] args) {
        System.out.println(new Count(new Const(7)).evaluate(0));
    }
}
