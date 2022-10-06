public class Sum {
    public static void main(String[] args) {
        double summa = 0;
	for (String text: args) {
            char[] textArray = text.toCharArray();
            String digit = "";
            for (char symbol : textArray) {
                if (symbol = "." | symbol == '0' | symbol == '1' | symbol == '2' | symbol == '3' | symbol == '4' | symbol == '-' |
                        symbol == '5' | symbol == '6' | symbol == '7' | symbol == '8' | symbol == '9') {
                    digit = digit + symbol + "";
                } else {
                    if (digit.length() > 0) {
                        summa += Double.parseDouble(digit);
                        digit = "";
                    }
                }
            }
            if (digit.length() > 0) {
                summa += Double.parseDouble(digit);
                digit = "";
            }
        }
        System.out.println(summa);
    }
}
	