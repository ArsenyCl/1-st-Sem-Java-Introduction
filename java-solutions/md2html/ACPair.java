package md2html;

public class ACPair {
    private static int intValue;
    private static String strValue;
    ACPair(int intIn, String strIn) {
        intValue = intIn;
        strValue = strIn;
    }
    public static void setIntValue(int in) {
        intValue = in;
    }
    public static void setStrValue(String in) {
        strValue = in;
    }
    public static int getIntValue() {
        return intValue;
    }
    public static String getStrValue() {
        return strValue;
    }
    public int hasCode() {
         return 0;
    }
}
