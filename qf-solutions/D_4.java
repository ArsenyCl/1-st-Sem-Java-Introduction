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
            out += (( modPow(k, (long)Math.ceil((float) l / 2), mod)) * (modPow(k, (long) (Math.ceil((float) (n - l) / 2)),  mod)) % mod);
            out = out % mod;
        }
        return out;
    }
    private static long D(int n, int k) {
        long out = R(n, k) % mod;
        long minusOut = 0;
        for (int l = 1; l < n; l++) {
            if (n % l == 0) {
                minusOut += ((n / l) * D(l, k)) % mod;
                minusOut = minusOut % mod;
            }
        }
        return (out-minusOut) % mod;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        long answer = 0;
        for (int i = 1; i <= n; i++) {
            for (int l = 1; l <= i; l++) {
                if (i % l == 0) {
                    answer += D(l, k) % mod;
                    answer = answer %  mod;
                }
            }
        }
        System.out.println(answer);
    }
}
