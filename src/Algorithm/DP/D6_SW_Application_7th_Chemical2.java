package Algorithm.DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class D6_SW_Application_7th_Chemical2 {
    /*

     */
    public static void main(String args[]) throws Exception {
        StringBuilder ans = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            ans.append("#").append(test_case).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            map = new int[n][n];
            v = new boolean[n][n];
            matrix = new HashMap<>();

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++)
                    map[i][j] = Integer.parseInt(st.nextToken());
            }

            // 1. 행렬 정의 : 직사각형 - 범위로 파악
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    if (!v[i][j] && map[i][j] > 0) define(i, j);

            // 2. 곱 순서 정의
            m = matrix.size();
            int[] sequence = new int[m + 1];
            for (int key : matrix.keySet()) if (!matrix.containsValue(key)) sequence[0] = key;
            for (int i = 1; i <= m; i++)
                sequence[i] = matrix.get(sequence[i - 1]);

            // 3. 최소 찾기 : (B*A)*C = ((24) 40) / B*(A*C) = (30 (60)) !!!MST로 만들면 되지 않을까? A x B * B x C / i-j 가중치 A x B x C
            // AxB BxC CxD ABCD : 구간 점화식 - 시작부터 m까지 k 기준으로 덩어리 분할
            int[][] table = new int[m][m]; // 첫 시작 k개: 원소-1 번 행렬곱 발생, k-1!번 경우의 수 존재: 누구를 먼저 선택하느냐!를 기준으로 각 점화식

            for (int cnt = 1; cnt < m; cnt++) { // 행렬곱 수행한 행렬 수: 1) A(axb) 2) AB(axbxc)
                for (int i = 0; i < m - cnt; i++) {
                    int j = i + cnt;
                    table[i][j] = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        int option = table[i][k] + table[k+1][j] + sequence[i] * sequence[k+1] * sequence[j+1];   // j+1로 하거나 j를 cnt+1로.. 이거 때문에 한 시간
                        table[i][j] = Math.min(option, table[i][j]);    // table[i][k], [k+1][j]로 하지 않으면 그리디가 된다.
                    }
                }
            }
            ans.append(table[0][m - 1]).append('\n');
        }
        System.out.print(ans);
    }

    static int[][] map;
    static int n, m;
    static boolean[][] v;
    static Map<Integer, Integer> matrix;

    public static void define(int r, int c) {

        int a = n - 1, b = n - 1;
        for (int i = r; i < n - 1; i++)
            if (map[i + 1][c] == 0) {
                a = i;
                break;
            }

        for (int i = c; i < n - 1; i++)
            if (map[r][i + 1] == 0) {
                b = i;
                break;
            }

        for (int i = r; i <= a; i++)
            for (int j = c; j <= b; j++)
                v[i][j] = true;

        matrix.put(a - r + 1, b - c + 1);
    }
}