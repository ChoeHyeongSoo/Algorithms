package Algorithm.BackTracking.D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class D3_2806_NQueen {
    /*
    N*N 보드에 N개의 퀸을 서로 다른 두 퀸이 공격하지 못하게 놓는 경우의 수는 몇 가지가 있을까?
     */
    public static void main(String args[]) throws Exception {
        StringBuilder ans = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            ans.append("#").append(test_case).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            col = new int[n];
            cnt = 0;

            ans.append('\n');
        }
        System.out.print(ans);
    }

    static int[] col;
    static int n, cnt;

    public static void make(int r) {
        if (r==n) {
            cnt++; return;
        }

        for (int i = 0; i < n; i++) {
            col[r] = i;

            if (is_possible(r))
                make(r+1);
        }
    }

    public static boolean is_possible(int r) {
        for (int i = 0; i < r; i++) {
            if (col[i]==col[r]) return false;
            if (Math.abs(r-i)==Math.abs(col[r]-col[i])) return false;
        }
        return true;
    }
}