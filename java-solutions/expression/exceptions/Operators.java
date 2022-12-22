package expression.exceptions;

import expression.parser.HashBor;

public class Operators {
    protected static final HashBor binaryOperators = new HashBor("+", "-", "*", "/", "set", "clear"); //список бинарных операций
    protected static final HashBor unaryOperators = new HashBor("-", "count"); //список унарных операций
}
