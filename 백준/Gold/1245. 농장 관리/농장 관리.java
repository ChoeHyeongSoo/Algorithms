import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n+2][m+2];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= m; j++) arr[i][j] = Integer.parseInt(st.nextToken());
		}
		
		visit = new boolean[n+2][m+2];
		
		
		int ans = 0;
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= m; j++)
				if (!visit[i][j] && arr[i][j]>0 && bfs(i, j)) ans++; // 미방문 & 0 이상만 bfs 진입
		
		System.out.println(ans);
	}
	
	static int n, m;
	static int[][] arr, dir = {{1,1,1,0,-1,-1,-1,0}, {1,0,-1,-1,-1,0,1,1}};
	static boolean[][] visit;
	static Deque<Integer> q = new ArrayDeque<>();
	
	public static boolean bfs(int r, int c) {
		
		boolean ok = true;
		visit[r][c] = true;
		q.offer(r*(m+2) + c);
		
		while (!q.isEmpty()) {
			
			int curr = q.poll();
			
			int tmp_r = curr/(m+2), tmp_c = curr%(m+2);
			
			for (int d = 0; d < 8; d++) {
				
				int next_r = tmp_r + dir[0][d];
				int next_c = tmp_c + dir[1][d];

				if (arr[next_r][next_c]==0) continue;	// 범위 초과는 먼저 필터링
				if (arr[next_r][next_c] > arr[r][c]) ok = false;	// 순서 중요 : 방문을 했어도 인접칸에 큰 값이 있는지 반드시 체크
				if (visit[next_r][next_c] || arr[next_r][next_c]!=arr[r][c]) continue; 
				
				visit[next_r][next_c] = true;
				q.offer(next_r * (m+2) + next_c);
			}
			
		}

		return ok;
	}
}
