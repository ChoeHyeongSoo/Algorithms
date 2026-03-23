import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_9370_LogicComplete {
//public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken()), t = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()), g = Integer.parseInt(st.nextToken()), h = Integer.parseInt(st.nextToken());
            int[][] adj = new int[n + 1][n + 1];
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int curr = Integer.parseInt(st.nextToken()), next = Integer.parseInt(st.nextToken()), weight = Integer.parseInt(st.nextToken());
                adj[curr][next] = weight;
            }
            int[] candidate = new int[t];
            for (int i = 0; i < t; i++) candidate[i] = Integer.parseInt(st.nextToken());

            // g-h 거쳐서 간 거리의 가중치 < 가장 적은 노드를 거치고 간 가중치 이면 가능 djikstra vs bfs(bfs도 같은 레벨까지 체크해준 뒤 min()) 가중치 비교

            sb.append('\n');
        }
        System.out.println(sb);
    }
    static int n;
}