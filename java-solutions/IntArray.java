import java.util.Arrays;

public class IntArray {
    private int[] array;
    private int arLength;
    public IntArray(int len) {
        array = new int[len];
        arLength = 0;
    }
    private void multArray(int[] ar) {
        array = Arrays.copyOf(ar, ar.length * 2);
    }
    public void append(int a) {
        if (arLength == array.length) {
            multArray(array);
        }
        array[arLength] = a;
        arLength++;
    }
    public int get(int index) {
        if (index < arLength) {
            return array[index];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }
    public void set(int index, int value ) {
        if (index < arLength) {
            array[index] = value;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }
    public int size() {
        return arLength;
    }
}
