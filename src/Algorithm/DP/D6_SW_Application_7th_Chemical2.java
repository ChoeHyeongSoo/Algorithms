package Algorithm.DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class D6_SW_Application_7th_Chemical2 { // 1260.
    /*
    창고에는 화학 물질 용기 n2개가 n x n으로 배열되어 있었다.
    빈 용기에 해당하는 원소는 ‘0’으로 저장하고, 화학 물질이 들어 있는 용기에 해당하는 용기는 화학 물질의 종류에 따라 ‘1’에서 ‘9’사이의 정수를 저장했다.
    1. 화학 물질이 담긴 용기들이 사각형을 이루고 있다. 또한, 사각형 내부에는 빈 용기가 없다.
    2. 2개의 화학 물질이 담긴 용기들로 이루어진 사각형들 사이에는 빈 용기들이 있다.
    2개의 행렬 원소 간 곱셈은 2개의 행렬 원소에 대응되는 화학 물질을 섞을 것이다. 단, 섞은 물질들을 합치는 데는 시간이 걸리지 않는다고 가정한다.
    3개의 행렬 (A(3x4), B(2x3), C(4x5))의 곱셈을 살펴보면, 행렬간 곱셈을 수행하기 위해 반드시 BxAxC로 곱셈이 수행되어야 한다.
    (B*A)*C, 즉, B*A를 먼저 수행하고 그 결과 행렬을 C 와 곱하면, 64번의 원소간 곱셈이 수행된다. 그러나 B*(A*C)의 경우는 90번의 곱셈이 필요하다.
    창고의 용기들에 대한 2차원 배열에서 행렬(화학 물질이 든 용기들로 이루어진 사각형)들을 찾아내고,
    찾아낸 행렬들 간의 곱셈에 필요한 최소 원소간의 곱셈 수 (2개의 화학 물질이 든 용기를 섞는 작업의 수)를 찾는 프로그램을 작성하시오.
    [제약 사항]
    부분 행렬의 열의 개수는 서로 다르며 행의 개수도 서로 다르다. 따라서, 고유한 행렬곱 순서를 가진다.
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

            // 2. 행렬 순서 정의
            m = matrix.size();
            int[] sequence = new int[m + 1];
            for (int key : matrix.keySet()) if (!matrix.containsValue(key)) sequence[0] = key;
            for (int i = 1; i <= m; i++)
                sequence[i] = matrix.get(sequence[i - 1]);

            // 3. 최소 찾기 : (B*A)*C = ((24) 40) / B*(A*C) = (30 (60)) : AxB BxC CxD ABCD : 구간 점화식 - 시작부터 m까지 k 기준으로 덩어리 분할
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
            if (map[i + 1][c] == 0) { a = i; break; }

        for (int i = c; i < n - 1; i++)
            if (map[r][i + 1] == 0) { b = i; break; }

        for (int i = r; i <= a; i++)
            for (int j = c; j <= b; j++)
                v[i][j] = true;

        matrix.put(a - r + 1, b - c + 1);   // key: Row / value: Column으로 map에 저장
    }
}