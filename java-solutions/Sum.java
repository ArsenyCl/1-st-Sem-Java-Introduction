public class Sum {
    public static void main(String[] args) {
        ;
        int summa = 0;
        for (String text : args) {
            int i = 0;
            while (i < text.length()) {
                int nachalo;
                if (!Character.isWhitespace(text.charAt(i))) {
                    nachalo = i;
                    while (i < text.length() && !Character.isWhitespace(text.charAt(i))) {
                        i++;
                    }
                    summa += Integer.parseInt(text.substring(nachalo, i));
                }
                i++;
            }
        }
        System.out.println(summa);
    }
}
