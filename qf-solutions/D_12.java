import java.util.*;


public class TASK_D {
    static long mod = 998244353;
    public static long modPow(long osn, long step) {
        long out = 1;
        while (step > 0) {
            if ((step & 1) != 0) {
                out = (out * osn % mod);
            }
            osn = (osn * osn % mod);
            step  = step >>> 1;
        }
        return out;
    }
    public static long modMinus(long a, long b) {
        return ((a-b) % mod + mod) % mod;
    }
    public static long modSum(long a, long b) {
        return (a + b) % mod;
    }
    public static List<Integer> countDivisors(long x) {
        List<Integer> divisors = new ArrayList<>();
        for (int i = 1; (long)i * i <= x; i++) {
            if (x % i == 0) {
                divisors.add(i);
                if (x / i != i) {
                    divisors.add((int)x / i);
                }
            }
        }
        return divisors;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        long[] arrR = new long[n+1];
        long[] arrD = new long[n+1];
        for (int i = 1; i <= n; i++) {
            if ((i & 1) == 0) {
                arrR[i] = (i / 2) * modSum((modPow(k, i / 2)), modPow(k, (i / 2) + 1)) % mod;
            } else {
                arrR[i] = ((long) i * modPow(k, (i+1)/2)) % mod;
            }
        }
        arrD[1] = arrR[1];
        for(int i = 2; i <= n; i++) {
            arrD[i] = arrR[i];
            for (int l: countDivisors(i)) {
                if (l < i) {
                    arrD[i] = modMinus(arrD[i], (long) i / l * arrD[l]);
                }
            }
        }
        long result = 0;
        for(int i = 1; i<=n; i++) {
            for(int divisor: countDivisors(i)) {
                result = modSum(arrD[divisor], result);
            }
        }
        System.out.println(result);
    }
}