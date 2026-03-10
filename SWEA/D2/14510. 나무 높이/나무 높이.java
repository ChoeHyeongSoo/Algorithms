import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
    public static void main(String args[]) throws Exception {
        StringBuilder ans = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            ans.append("#").append(test_case).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            tree = new int[n];
            max = 0; sum = 0;
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                tree[i] = Integer.parseInt(st.nextToken());
                if (tree[i] > max) max = tree[i];
                sum+=tree[i];  // 큐로 되면 그냥 트리 지워도 됨
            }

            int day = 0;
            int even = 0, odds = 0;
            for (int i = 0; i < n; i++) {
                int diff = max - tree[i];
                if (diff==0) continue;

                even += diff/2;
                odds += diff%2;
            }

            while (even > odds+1) {
                even--; odds+=2;
            }

            if (even==odds) {
                day = even + odds;
            } else if (even > odds) {
                day = even*2;
            } else {
                day = odds*2 - 1;
            }

            ans.append(day).append('\n');
        }
        System.out.print(ans);
    }

    static int n, max, sum;
    static int[] tree;
}