public class SumDouble {
    public static void main(String[] args) {
        double summa = 0;
        for (String text : args) {
            int i = 0;
            while (i < text.length()) {
                int nachalo;
                if (!Character.isWhitespace(text.charAt(i))) {
                    nachalo = i;
                    while (i < text.length() && !Character.isWhitespace(text.charAt(i))) {
                        i++;
                    }
                    summa += Double.parseDouble(text.substring(nachalo, i));
                }
                i++;
            }
        }
        System.out.println(summa);
    }
}