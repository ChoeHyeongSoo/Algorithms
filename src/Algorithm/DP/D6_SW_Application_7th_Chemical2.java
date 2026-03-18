package Algorithm.DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

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
            matrix = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++)
                    map[i][j] = Integer.parseInt(st.nextToken());
            }

            // 1. 행렬 정의 : 직사각형 - 범위로 파악
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    if (!v[i][j]&&map[i][j]>0) define(i, j);

            // 2. 곱 순서 정의 : 간선화
            k = matrix.size();
            List<Integer> edges = new LinkedList<>();
            boolean[] check = new boolean[k];
            edges.add(0); check[0] = true;
            while (edges.size() < k) {
                for (int i = 1; i < k; i++) {
                    if (check[i]) continue;
                    position curr = matrix.get(i);
                    if (curr.a==edges.getLast()) {
                        edges.addLast(i);
                        check[i] = true;
                    } else if (curr.b==edges.getFirst()) {
                        edges.addFirst(i);
                        check[i] = true;
                    }
                }
            }

            // 3. 최소 찾기 : (B*A)*C = ((24) 40) / B*(A*C) = (30 (60)) !!!MST로 만들면 되지 않을까? A x B * B x C / i-j 가중치 A x B x C
            int matmul_cnt = 0;
            int[] sequence = new int[k];
            for (int i = 0; i < k; i++) sequence[i] = edges.get(i);


            int[][] table = new int[k-1][k]; // 원소-1 번 행렬곱 발생, k-1!번 경우의 수 존재: 누구를 먼저 선택하느냐!를 기준으로 각 점화식


            ans.append(matmul_cnt).append('\n');
        }
        System.out.print(ans);
    }

    static int[][] map;
    static int n, k;
    static boolean[][] v;
    static List<position> matrix;

    public static void define(int r, int c) {

        int a = n-1, b = n-1;
        for (int i = r; i < n-1; i++)
            if (map[i+1][c]==0) {a=i; break;}

        for (int i = c; i < n-1; i++)
            if (map[r][i+1]==0) {b=i; break;}

        for (int i = r; i <= a; i++)
            for (int j = c; j <= b; j++)
                v[i][j] = true;

        matrix.add(new position(a-r+1, b-c+1));
    }
}

class position {
    int a, b;

    public position(int a, int b) {
        this.a = a;
        this.b = b;
    }
}