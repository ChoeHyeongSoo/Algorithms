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
            table = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) table[i][j] = Integer.parseInt(st.nextToken());
            }

            select = 0;
            min = Integer.MAX_VALUE;
            comb(0, 0);

            ans.append(min).append('\n');
        }
        System.out.print(ans);
    }

    static int n, min;
    static int[][] table;
    static int select;

    public static void comb(int depth, int idx) {

        if (depth==n/2) {
            min = Math.min(min, cal());
            return;
        }

        for (int i = idx; i < n; i++) {
            select = select | (1<<i);
            comb(depth+1, i+1);
            select = select & ~(1<<i);
        }
    }

    public static int cal() {

        int a = 0, b = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((select & (1<<i))!=0 && (select & (1<<j))!=0) {
                    a+=table[i][j];
                } else if ((select & (1<<i))==0 && (select & (1<<j))==0)
                    b+=table[i][j];
            }
        }

        return Math.abs(a-b);
    }

}