package Algorithm.BackTracking.Combination;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class SW_Test_A_Cook {
    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("Algorithm/BackTracking/Combination/input_SW_Test_A_Cook.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            int n = Integer.parseInt(br.readLine());
            int[][] s = new int[n][n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++)
                    s[i][j] = Integer.parseInt(st.nextToken());
            }
            boolean[] select = new boolean[n];
            min = Integer.MAX_VALUE;
            select(0, 0, n, select, s);
            System.out.println("#" + test_case + " " + min);
        }
    }

    static int min;
    public static void select(int cnt, int start, int n, boolean[] v, int[][] s) {

        if (cnt==n/2) {
            // 값 계산 함수 진입 : min 갱신
            int[] selected = new int[n/2], leftover = new int[n/2]; // 선택이 2개 이상일 때, 부분 집합에서 2개 선택하여 점수 합산
            int idx_select = 0, idx_leftover = 0;
            for (int i = 0; i < n; i++) {
                if (v[i])
                    selected[idx_select++] = i;
                if (!v[i])
                    leftover[idx_leftover++] = i;
            }

            int[] subset_s = new int[2], subset_l = new int[2]; // n/2개중 2개 선택하기 위한 서브셋
            int sum_selected = comb(0, 0, n/2, s, selected, subset_s, 0);
            int sum_leftover = comb(0, 0, n/2, s, leftover, subset_l, 0);
            int curr = Math.abs(sum_selected - sum_leftover);   // 차이 계산 후 갱신
            if (curr < min) min = curr;
            return;
        }

        for (int i = start; i < n; i++) {
            v[i] = true;
            select(cnt+1, i+1, n, v, s);
            v[i] = false;   // 선택했던 건 초기화해야 한다. 지나온 인덱스는 어차피 돌아가지 않을 것
        }
    }

    public static int comb(int cnt, int start, int num, int[][] s, int[] g, int[] subset, int sum) {

        if (cnt==2) {
            sum += s[subset[0]][subset[1]] + s[subset[1]][subset[0]];   // 누적은 여기서! for문에서 누적하면 n/2번 더 덮어써짐
            return sum;
        }

        for (int i = start; i < num; i++) {   // num: n/2가 넘어올것
            subset[cnt] = g[i];
            sum = comb(cnt+1, i+1, num, s, g, subset, sum);
        }

        return sum;
    }
}