package expression.parser;

import expression.*;

import java.util.Stack;


public final class ExpressionParser implements TripleParser {
    public TripleExpression parse(final String source) {
//        System.err.println(source + "  /***");
        return parse(new StringSource(source));
    }

    public static TripleExpression parse(final CharSource source) {
        return (TripleExpression) new Parser(source).parseExpression();
    }

    private static final String notOp = ")";

    private static class Parser extends BaseParser {
        public Parser(final CharSource source) {
            super(source);
        }

        private Object parseExpression() {
            Stack<Express> expressions = new Stack<>();
            Stack<String> operations = new Stack<>();
            expressions.push((Express) parseExpres());
            skipWhitespace();
            if (eof() || test(')')) {
                return expressions.peek();
            }
            String operator = parseOperator(Operators.binaryOperators);
            while (!operator.equals(notOp)) {
                operations.push(operator);
                expressions.push((Express) parseExpres());
                skipWhitespace();
                if (test(')') || eof()) {
                    mergeStack(expressions, operations, -1);
                    break;
                }
                operator = parseOperator(Operators.binaryOperators);
                if (ExpressOperation.getPriority(operations.peek()) >= ExpressOperation.getPriority(operator)) {
                    mergeStack(expressions, operations, ExpressOperation.getPriority(operator));
                }
            }
            return expressions.peek();
        }

        private Object parseExpres() {
            skipWhitespace();
            String unaryOperator = parseOperator(Operators.unaryOperators); //нужен крутой способ быстро проверять данные
            if (between('0', '9') && unaryOperator.equals("-")) {
                return parseConstOrVar(true);
            }
            skipWhitespace();
            if (take('(')) {
                Express toReturn = mergeUn((Express) parseExpression(), unaryOperator);
                skipWhitespace();
                take(')');
                return toReturn;
            } else if (between('0', '9') || between('x', 'z')) {
                return mergeUn(parseConstOrVar(false), unaryOperator);
            } else {
                return mergeUn((Express) parseExpres(), unaryOperator);
            }
        }

        private String parseOperator(HashBor in) {
            skipWhitespace();
            StringBuilder res = new StringBuilder();
            while (!eof() && in.contains(res.toString() + curCh())) {
                res.append(take());
            }
            return res.toString();
        }


        private Express parseConstOrVar(boolean unMin) {
            StringBuilder sb = new StringBuilder();
            if (unMin) {
                sb.append("-");
            }
            sb.append(curCh());
            if (between('x', 'z')) {
                take();
                while (!eof() && between('x', 'z')) {
                    sb.append(take());
                }
                return new Variable(sb.toString());
            } else if (between('0', '9')) {
                take();
                while (!eof() && between('0', '9')) {
                    sb.append(take());
                }
                return new Const(Integer.parseInt(sb.toString()));
            } else {
                throw error("Input mistake(parseOrConst)");
            }
        }

        private Express mergeBin(Express first, Express second, String operation) {
            return switch (operation) {
                case "+" -> new Add(first, second);
                case "-" -> new Subtract(first, second);
                case "/" -> new Divide(first, second);
                case "*" -> new Multiply(first, second);
                case "set" -> new Set(first, second);
                case "clear" -> new Clear(first, second);
                default -> {
                    System.err.println(first + " " + second + " " + operation);
                    throw error("Input mistake(merge)");
                }
            };
        }

        private Express mergeUn(Express first, String operation) {
            return switch (operation) {
                case "-" -> new UnaryMinus(first);
                case "count" -> new Count(first);
                default -> first;
            };
        }

        private void mergeStack(Stack<Express> expressions, Stack<String> operations, int prioriry) {
            while (operations.size() > 0 && ExpressOperation.getPriority(operations.peek()) >= prioriry) {
                Express first = expressions.pop();
                Express second = expressions.pop();
                String operation = operations.pop();
                expressions.push(mergeBin(second, first, operation));
            }
        }

        private void skipWhitespace() {
            while (Character.isWhitespace(curCh())) {
                take();
            }
        }
    }
}
