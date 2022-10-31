import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TASK_D {
    static long mod = 998244353;
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
            out += (modPow(k, (long)Math.ceil((float) l / 2), mod) *  (modPow(k, (long) (Math.ceil((float) (n - l) / 2)),  mod) + mod)) % mod;
            out = (out + mod) % mod;
        }
        return out;
    }
    private static long D(int n, int k) {
        long out = R(n, k);
        if (n == 1) {
            return out;
        } else {
            long minusOut = 0;
            minusOut += (n  * D(1, k) + mod) % mod;
            minusOut = (minusOut + mod) % mod;
            for (int l = 2; l * l <= n; l++) {
                if (n % l == 0) {
                    minusOut += (n / l * D(l, k) + mod) % mod;
                    minusOut = (minusOut + mod) % mod;
                    if (l * l != n) {
                        minusOut += (n / (n / l) * D((n / l), k) + mod) % mod;
                        minusOut = (minusOut + mod) % mod;
                    }
                }
            }
            return (out - minusOut + mod) % mod;
        }
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