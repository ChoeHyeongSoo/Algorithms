import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
    public static void main(String args[]) throws Exception {
        StringBuilder ans = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        for (int test_case = 1; test_case <= T; test_case++) {
            ans.append("#").append(test_case).append('\n');
            int n = Integer.parseInt(br.readLine().trim()) / 10;
            int[] money = new int[n+1];
            int[] use = new int[n+1];

            Arrays.fill(money, Integer.MAX_VALUE);
            money[0] = 0;
            use[0] = 0;

            for (int i = 1; i <= n; i++) {
                for (int c : each) {
                    if (i < c) continue;
                    if (money[i-c]==Integer.MAX_VALUE) continue;
                    if (money[i] >= money[i-c] + 1) {
                        money[i] = money[i-c] + 1;
                        use[i] = c;
                    }
                }
            }

//            Map<Integer, Integer> cnt = new HashMap<>();
//            for (int c : each) cnt.put(c, 0);
            int[] cnt = new int[5001];
            int idx = n;
            while (idx > 0) {
//                cnt.put(use[idx], cnt.get(use[idx])+1);
                cnt[use[idx]]++;
                idx-=use[idx];
            }

            for (int i = 7; i >= 0; i--) ans.append(cnt[each[i]]).append(" ");

            ans.append('\n');
        }
        System.out.print(ans);
    }

    static int[] each = {1, 5, 10, 50, 100, 500, 1000, 5000};
}