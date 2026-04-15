import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());   // 3<=n,m<=8
        arr = new int[n+2][m+2];
        for (int i = 0; i < n+2; i++) arr[i][0] = arr[i][m+1] = 1;
        for (int i = 0; i < m+2; i++) arr[0][i] = arr[n+1][i] = 1;

        // 1. 입력
        virus = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j]==2) virus.add(i*(m+2) + j);   // (k/m, k%m) 디코딩
                if (arr[i][j]==1) wall_cnt++;   // 안전 영역 계산 : 그리드 - (퍼진 바이러스 수 + 벽 수)
            }   // 바이러스 위치를 리스트에 받을까 말까
        }

        // 바이러스 2 <= v <= 10 / 3<=n,m<=8 / 벽 최대 3개 : 퍼져나가는 걸 막을 수 있는 최소방어선 형성
        // Combination : 2차원 -> 1차원, 각 경우의 수 체크
        min = Integer.MAX_VALUE;
        comb(0, 1);
        System.out.println(n*m - (wall_cnt+3) - min);   //추가로 세워진 벽 3개 카운팅
    }
    static int n, m, min, wall_cnt;
    static int[][] arr;
    static List<Integer> virus;

    static void comb(int depth, int idx) {

        if (depth==3) {
            min = Math.min(safety_room(), min);
            return;
        }

        for (int i = idx+1; i < (n+2)*(m+2); i++) {
            if (arr[i/(m+2)][i%(m+2)]==1 || arr[i/(m+2)][i%(m+2)]==2) continue;
            arr[i/(m+2)][i%(m+2)]=1;
            comb(depth+1, i);
            arr[i/(m+2)][i%(m+2)]=0;
        }
    }

    static int[][] dir = {{1,0,-1,0},{0,1,0,-1}};

    static int safety_room() { // 0을 셀 필요 없이, BFS로 퍼진 2와 1의 개수 카운팅 ?

        int cnt = 0;
        Deque<Integer> q = new ArrayDeque<>();
        boolean[][] visit = new boolean[n+2][m+2];

        for (int k : virus) {   // 2의 개수를 최소화하는 방향으로 하면 가지치기가 가능!
            q.add(k);
            while (!q.isEmpty()) {

                int curr = q.poll();
                cnt++;

                if (cnt > min) return Integer.MAX_VALUE;

                for (int d = 0; d < 4; d++) {
                    int r = curr/(m+2) + dir[0][d];
                    int c = curr%(m+2) + dir[1][d];

                    if (arr[r][c]==1 || arr[r][c]==2) continue;
                    if (visit[r][c]) continue;

                    visit[r][c] = true;
                    q.offer(r*(m+2)+c);
                }
            }
        }
        return cnt;
    }
}