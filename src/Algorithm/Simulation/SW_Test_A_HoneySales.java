package Algorithm.Simulation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class SW_Test_A_HoneySales { // 2115.
    /*
    N*N 개의 벌통이 정사각형 모양으로 배치되어 있다.
    각 벌통에 있는 꿀의 양이 주어졌을 때, 다음과 같은 과정으로 벌꿀을 채취하여 최대한 많은 수익을 얻으려고 한다.
    ① 두 명의 일꾼이 있다. 꿀을 채취할 수 있는 벌통의 수 M이 주어질 때,
      각각의 일꾼은 가로로 연속되도록 M개의 벌통을 선택하고, 선택한 벌통에서 꿀을 채취할 수 있다.
      단, 두 명의 일꾼이 선택한 벌통은 서로 겹치면 안 된다.
    ② 두 명의 일꾼은 선택한 벌통에서 꿀을 채취하여 용기에 담아야 한다.
      단, 서로 다른 벌통에서 채취한 꿀이 섞이게 되면 상품가치가 떨이지게 되므로, 하나의 벌통에서 채취한 꿀은 하나의 용기에 담아야 한다.
      하나의 벌통에서 꿀을 채취할 때, 일부분만 채취할 수 없고 벌통에 있는 모든 꿀을 한번에 채취해야 한다. 두 일꾼이 채취할 수 있는 꿀의 최대 양은 C 이다.
    ③ 채취한 꿀은 시장에서 팔리게 된다. 이때 하나의 용기에 있는 꿀의 양이 많을수록 상품가치가 높아, 각 용기에 있는 꿀의 양의 제곱만큼의 수익이 생긴다.
     */
    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("Algorithm/Simulation/input_SW_Test_A_HoneySales.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            int[][] honey = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++)
                    honey[i][j] = Integer.parseInt(st.nextToken());
            }

            int[][] honeymoney = new int[n][n - m + 1];
            for (int r = 0; r < n; r++) {
                for (int idx = 0; idx <= n - m; idx++) {
                    int[] tmp = new int[m]; // 길이 m인 부분조합 생성
                    int k_idx = 0;
                    for (int k = idx; k < idx + m; k++) // 배열 담아내기
                        tmp[k_idx++] = honey[r][k];
                    honeymoney[r][idx] = subset(0, 0, c, 0, 0, tmp);  // 부분조합의 최대이익 계산
                }
            }

            int max1 = 0, max2 = 0; // 최대1 최대2 게산..
            int[] p = new int[2];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n - m + 1; j++) {
                    if (honeymoney[i][j] > max1) {
                        max1 = honeymoney[i][j];
                        p[0] = i;
                        p[1] = j;
                    }
                }
            for (int i = 0; i < n; i++) {// r 같을 경우, c는 m만큼 더 커야 함
                for (int j = 0; j < n - m + 1; j++) {
                    if (honeymoney[i][j] > max2) {
                        if (i == p[0] && (Math.abs(j - p[1]) < m)) continue;
                        max2 = honeymoney[i][j];
                    }
                }
            }
            int ans = max1+max2;
            System.out.println("#" + test_case + " " + ans);
        }
    }

    // 부분 조합 : 길이 m인 부분 조합들 중에서 또다시 최대가 될 이익 계산해서 저장
    public static int subset(int depth, int sum, int c, int profit, int max, int[] set) {
        if (sum > c) {
            return max;
        }

        if (depth == set.length) {
            if (profit > max) max = profit;
            return max;
        }

        int exclude = subset(depth + 1, sum, c, profit, max, set);
        int include = subset(depth + 1, sum + set[depth], c, profit + (set[depth] * set[depth]), max, set);
        max = Math.max(include, exclude);

        return max;
    }
}