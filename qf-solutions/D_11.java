import java.util.*;


public class TASK_D {
    static long mod = 998244353;
    static HashMap<ArrayList<Integer>, Long> mapR = new HashMap<>();
    static HashMap<ArrayList<Integer>, Long> mapD = new HashMap<>();
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
        ArrayList<Integer> key  = new ArrayList<>();
        key.add(n);
        key.add(k);
        mapR.putIfAbsent(key, out);
        return out;
    }
    private static long D(int n, int k) {
        ArrayList<Integer> key  = new ArrayList<>();
        key.add(n);
        key.add(k);
        long out = mapR.getOrDefault(key, R(n, k));
        if (n == 1) {
            return out;
        } else {
            long minusOut = 0;
            key.set(0, 1);
            minusOut += (n  * mapD.getOrDefault(key, D(1, k)) + mod) % mod;
            minusOut = (minusOut + mod) % mod;
            for (int l = 2; l * l <= n; l++) {
                if (n % l == 0) {
                    key.set(0, l);
                    minusOut += (n / l *  mapD.getOrDefault(key, D(l, k)) + mod) % mod;
                    minusOut = (minusOut + mod) % mod;
                    if (l * l != n) {
                        key.set(0, n / l);
                        minusOut += (n / (n / l) *  mapD.getOrDefault(key, D(n / l, k)) + mod) % mod;
                        minusOut = (minusOut + mod) % mod;
                    }
                }
            }
            key.set(0, n);
            out =  (out - minusOut + mod) % mod;
            mapD.putIfAbsent(key, out);
            return out;
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