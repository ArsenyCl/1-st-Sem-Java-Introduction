package expression.parser;
public class Main {
    public static void main(String[] args) {
        HashBor bor = new HashBor("count");
        System.out.println(new ExpressionParser().parse("count 0"));
    }
}
//((((1329812979 / z) + (-1244362750 + x)) - -(-((z - 1491042357)))) / -((-((-1038349242 + (x - -1593370841))) - ((y / -1924590086) + -(33326613)))))
