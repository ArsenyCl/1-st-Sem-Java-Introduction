package expression.parser;

public class Operators {
    protected static final HashBor binaryOperators = new HashBor("+", "-", "*", "/", "set", "clear"); //список бинарных операций
    protected static final HashBor unaryOperators = new HashBor("-", "count"); //список унарных операций
}
