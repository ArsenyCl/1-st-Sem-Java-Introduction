package expression.parser;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Operators {
    protected static final HashBor binaryOperators = new HashBor("+", "-", "*", "/", "set", "clear"); //список бинарных операций
    protected static final HashBor unaryOperators = new HashBor("-", "count"); //список унарных операций
}
