import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int[][][] table = new int[n+1][10][1<<10]; // [length][last number][state]

        // 1. Init table
        for (int i = 1; i < 10; i++) table[1][i][1<<i] = 1;

        // 2. last가 +-1인 len-1의 state 중, 1<<현재 |
        for (int len = 2; len <= n; len++) {
            for (int last = 0; last < 10; last++) {
                for (int state = 0; state < 1<<10; state++) { // 비트마스킹 : 직전 상태의 경우의 수 병합
                    // 0&9는 올 수 있는 경우가 하나씩
                    if (last>0 && table[len-1][last-1][state]>0) // 0은 last-1이 없다
                        table[len][last][state|(1<<last)] = (table[len][last][state|(1<<last)] + table[len-1][last-1][state]) % MOD;
                    if (last<9 && table[len-1][last+1][state]>0) // 9는 last+1이 없다
                        table[len][last][state|(1<<last)] = (table[len][last][state|(1<<last)] + table[len-1][last+1][state]) % MOD;
                }   // 현재 마지막 수를 포함시킨 state|(1<<last)에 이전 마지막 수의 state가 존재하는 경우 누적
            }
        }
        int ans = 0;
        for (int i = 0; i < 10; i++) ans = (ans + table[n][i][(1<<10)-1]) % MOD;
        System.out.println(ans%MOD);
    }
    static int n;
    static final int MOD = 1000000000;
}