public class TASK_D {
    static long mod = 998244353;
    private static long R(int n, int k) {
        long out = 0;
        for (int l = 0; l < n; l++) {
            out += ((long)Math.pow(k, Math.ceil((float) l /2 )) * (long)Math.pow(k, Math.ceil((float) (n-l) /2 ))) % mod;
            out %= mod;
        }
        return out;
    }
    private static long D(int n, int k) {
        long out = R(n, k);
        for (int l = 1; l < n; l++) {
            if (n % l == 0) {
                out -= (((n / l) * D(l, k))) % mod;
                out %= mod;
            }
        }
        return out;
    }
    public static void main(String[] args) {
        int n = 42;
        int k = 7;
        long answer = 0;
        for (int i = 1; i  <= n; i++) {
            for (int l = 1; l <= i; l++) {
                if (i % l == 0) {
                    answer += D(l, k) % mod;
                    answer %= mod;
                }
            }
        }
        System.out.println(answer);
    }
}
