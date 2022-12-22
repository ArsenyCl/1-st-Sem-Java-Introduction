package expression.exceptions;

import expression.*;
import expression.parser.*;

import java.util.Stack;



public class ExpressionParser implements TripleParser {
        private static int psp;
        public TripleExpression parse(final String source) throws OverflowException {
            psp = 0;
//        System.err.println(source + "  /***");
            return parse(new StringSource(source));
        }
        public static TripleExpression parse(final CharSource source) throws OverflowException{
            TripleExpression toRet =  (TripleExpression) new Parser(source).parseExpression();
            if (psp < 0) {
                throw new InvalidInputException("No opening parenthesis");
            } else if(psp > 0) {
                throw new InvalidInputException("No closing parenthesis");
            }
            return toRet;
        }
        private static final String notOp = ")";
        private static class Parser extends BaseParser {
            public Parser(final CharSource source) {
                super(source);
            }
            private Object parseExpression() throws  OverflowException {
                Stack<Express> expressions = new Stack<>();
                Stack<String> operations = new Stack<>();
                expressions.push((Express) parseExpres());
                skipWhitespace();
                if (eof() || test(')')) {
                    psp = test(')') ? psp-1 : psp;
                    return expressions.peek();
                }
                String operator = parseOperator(Operators.binaryOperators);
                while (!operator.equals(notOp)) {
                    operations.push(operator);
                    expressions.push((Express) parseExpres());
                    skipWhitespace();
                    if (test(')') || eof()) {
                        psp = test(')') ? psp-1 : psp;
                        mergeStack(expressions, operations, -1);
                        break;
                    }
                    operator = parseOperator(Operators.binaryOperators);
                    if(ExpressOperation.getPriority(operations.peek()) >= ExpressOperation.getPriority(operator)) {
                        mergeStack(expressions, operations, ExpressOperation.getPriority(operator));
                    }
                }
                return expressions.peek();
            }
            private Object parseExpres() throws OverflowException {
                skipWhitespace();
                String unaryOperator = parseOperator(Operators.unaryOperators); //нужен крутой способ быстро проверять данные
                if(between('0', '9') && unaryOperator.equals("-")) {
                    return parseConstOrVar(true);
                }
                skipWhitespace();
                if (take('(')) {
                    psp++;
                    Express toReturn = mergeUn((Express)parseExpression(), unaryOperator);
                    skipWhitespace();
                    if (take(')')) {
                        return toReturn;
                    }
                    throw new InvalidInputException("No closing parenthesis");
                } else if (between('0', '9') || between('x', 'z')) {
                    return mergeUn(parseConstOrVar(false), unaryOperator);
                } else if (!unaryOperator.equals("")) {
                    return mergeUn((Express)parseExpres(), unaryOperator);
                } else {
                    throw new InvalidInputException("Missing argument");
                }
            }
            private String parseOperator(HashBor in) throws OverflowException {
                skipWhitespace();
                StringBuilder res = new StringBuilder();
                while (!eof() && in.contains(res.toString() + curCh())) {
                    res.append(take());
                }
                if (res.length() > 0 && ExpressOperation.getPriority(res.toString()) < 0) {
                    throw new InvalidInputException("Invalid order of operators or invalid operator");
                } else if ((in.equals(Operators.binaryOperators) && res.toString().equals(""))) {
                    throw new InvalidInputException("Wrong operators");
                } else if ( res.length() > 0 && Character.isLetter(res.charAt(0)) && !Character.isWhitespace(curCh()) && !test('(') && !test('-')) {
                    throw new InvalidInputException("Separate operators and operands");
                } else {
                    return res.toString();
                }
            }


            private Express parseConstOrVar(boolean unMin) {
                StringBuilder sb = new StringBuilder();
                if (unMin) {
                    sb.append("-");
                }
                sb.append(curCh());
                if (between('x', 'z')) {
                    take();
                    while (!eof() &&  between('x', 'z')) {
                        sb.append(take());
                    }
                    return  new Variable(sb.toString());
                } else if (between('0', '9')) {
                    take();
                    while (!eof() &&  between('0', '9')) {
                        sb.append(take());
                    }
                    try {
                        return new Const(Integer.parseInt(sb.toString()));
                    } catch (NumberFormatException e) {
                        throw new OverflowException("Constant overflow " + sb);
                    }
                } else {
                    throw new AssertionError("Unexpected behaviour in module: parseConstOrVar");
                }
            }
            private Express mergeBin(Express first, Express second, String operation) {
                return switch (operation) {
                    case "+" -> new CheckedAdd(first, second);
                    case "-" -> new CheckedSubtract(first, second);
                    case "/" -> new CheckedDivide(first, second);
                    case "*" -> new CheckedMultiply(first, second);
                    case "set" -> new CheckedSet(first, second);
                    case  "clear" -> new CheckedClear(first, second);
                    default -> throw new AssertionError("Unexpected behaviour in module: mergeBin");
                };
            }
            private Express mergeUn(Express first, String operation) {
                return switch (operation) {
                    case "-" -> new CheckedNegate(first);
                    case "count" -> new CheckedCount(first);
                    case "" -> first;
                    default -> throw new AssertionError("Unexpected behaviour in module: mergeUn");
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
