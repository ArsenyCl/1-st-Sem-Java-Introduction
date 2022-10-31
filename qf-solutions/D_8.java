import java.util.Scanner;
import java.math.BigInteger;

public class TASK_D {
    static long mod = 998244353;
    public static long modMult(long a, long b, long modulo) {
        return Long.parseLong((new BigInteger(String.valueOf(a)).multiply(new BigInteger(String.valueOf(b)))).add(new BigInteger(String.valueOf(modulo))).mod(new BigInteger(String.valueOf(modulo))).toString());
    }

    public static long modPow(long osn, long step, long modulo) {
        long out = 1;
        while (step > 0) {
            if ((step & 1) != 0) {
                out = (out * osn % modulo);
            }
            osn = (osn * osn % modulo);
            step  = step >>> 1;
        }
        return out;
    }

    private static long R(int n, int k) {
        long out = 0;
        for (int l = 0; l <= n-1; l++) {
            out += modMult(modPow(k, (long)Math.ceil((float) l / 2), mod), (modPow(k, (long) (Math.ceil((float) (n - l) / 2)),  mod)), mod);
            out = (out + mod) % mod;
        }
        return out;
    }
    private static long D(int n, int k) {
        long out = R(n, k);
        long minusOut = 0;
        for (int l = 1; l < (n / 2) + 1; l++) {
            if (n % l == 0) {
                minusOut += modMult(n / l, D(l, k), mod);
                minusOut = (minusOut + mod) % mod;
            }
        }
        return (out-minusOut + mod) % mod;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        in.close();
        long answer = 0;
        for (int i = 1; i <= n; i++) {
            for (int l = 1; l <= i; l++) {
                if (i % l == 0) {
                    answer += D(l, k);
                    answer = (answer + mod) %  mod;
                }
            }
        }
        System.out.println(answer);
    }
}