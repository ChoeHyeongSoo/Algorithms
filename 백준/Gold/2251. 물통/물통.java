import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		visit_dfs  = new boolean[a+1][b+1][c+1];
		
		dfs (0, 0, c);

		StringBuilder sb = new StringBuilder();
		Collections.sort(candidate);
		for (int k : candidate) sb.append(k + " ");
		System.out.println(sb);
	}
	
	static int a, b, c;
	static List<Integer> candidate = new ArrayList<>();
	static boolean[][][] visit_dfs;
	static boolean[][][] visit_bfs;
	
	static void bfs() {
		
		Deque<int[]> q = new ArrayDeque<>();
		
	}
	
	static void dfs(int tmp_a, int tmp_b, int tmp_c) {

		if (visit_dfs[tmp_a][tmp_b][tmp_c]) return;
		visit_dfs[tmp_a][tmp_b][tmp_c] = true;
		
		if (tmp_a==0) candidate.add(tmp_c);
		
		int c2b = Math.min(tmp_c, b-tmp_b);
		dfs(tmp_a, tmp_b+c2b, tmp_c-c2b);
		

		int c2a = Math.min(tmp_c, a-tmp_a);
		dfs(tmp_a + c2a, tmp_b, tmp_c-c2a);
		
		int b2a = Math.min(tmp_b, a-tmp_a);
		dfs(tmp_a + b2a, tmp_b-b2a, tmp_c);
		
		int b2c = Math.min(tmp_b, c-tmp_c);
		dfs(tmp_a, tmp_b-b2c, tmp_c+b2c);
		
		int a2b = Math.min(tmp_a, b - tmp_b);
		dfs(tmp_a - a2b, tmp_b + a2b, tmp_c);

		int a2c = Math.min(tmp_a, c - tmp_c);
		dfs(tmp_a - a2c, tmp_b, tmp_c+a2c);
	}
}
