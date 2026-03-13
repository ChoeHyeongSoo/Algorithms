import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] map;
	static int[][] visited;
	
	
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static List<Integer> sums;
	
	static boolean[] dfs_visited;
	
	static List<int[]> ladders;
	
	static int minAns = Integer.MAX_VALUE;
	
	static int mapCnt, n, m;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		mapCnt = 0;
		
		//섬 개수 세기
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				
				if(map[i][j] == 1) {
					mapCnt++;
					bfs(i, j);
				}
				
			}
		}
		
		
		//맵좌표만들기
		visited = new int[8][8];
		
		ladders = new ArrayList<>();
		
		//다리개수 저장
		int h = 0;
		//맵좌표저장
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j] != 0) {
					
					for(int d=0; d<4; d++) {
						int y = i;
						int x = j;
						
						int cnt = 0;
						 
						while(true) {
							y += dy[d];
							x += dx[d];
							
							if(y >=0 && y < n && x >= 0 && x < m) {
								if(map[y][x] == map[i][j]) break;								
								if(map[y][x] != 0) {
									
									int from = map[i][j];
									int to = map[y][x];
									
									if(cnt < 2) break;
									
									if(visited[from][to] != 0 && cnt < visited[from][to]){
										visited[from][to] = visited[to][from] = cnt;
										}
									
									else if(visited[from][to] == 0) {
										visited[from][to] = visited[to][from] = cnt;
										h++;	//다리 건설 성공
									}
									break;
								}
								cnt++;
							}
							
							else break;
						}	
							

					}
				}
			}
		}
		
		//사다리 리스트 만들기 (좌표, 개수 저장)
		
		for(int i=2; i<8; i++) {
			
			for(int j=i+1; j<8; j++) {
				
				if(visited[i][j] != 0) {
					ladders.add(new int[] {i, j, visited[i][j]});
				}
			}
		}
		
		//사다리 리스트 조합으로 최소비용구하기
		//사다리 개수는 섬개수(mapCnt)-1
		
		//사다리 개수가 최소가 안됨
		if(ladders.size() < mapCnt - 1) {
            System.out.println(-1);
        } else {
            // (수정됨) ladders.size() == mapCnt-1 인 경우도 무조건 DFS를 돌려 연결 여부를 검증해야 함
            dfs_visited = new boolean[ladders.size()];
            dfs(0, 0, 0);
            
            // 최솟값이 한 번도 갱신되지 않았다면 연결 불가능한 것
            if(minAns == Integer.MAX_VALUE) {
                System.out.println(-1);
            } else {
                System.out.println(minAns);
            }
        }
		
		
		
		
		
		
		
	}
	static void bfs(int y, int x) {
		Queue<Integer> q = new ArrayDeque<>();
		map[y][x] += mapCnt;
		
		q.add(y);
		q.add(x);
		
		while(!q.isEmpty()) {
			int r = q.poll();
			int c = q.poll();
			
			for(int d=0; d<4; d++) {
				int nr = r + dy[d];
				int nc = c + dx[d];
				
				if(nr >= 0 && nr < n && nc >= 0 && nc < m) {
					
					if(map[nr][nc] == 1) {
						map[nr][nc] += mapCnt;
						
						q.add(nr);
						q.add(nc);
					}
				}
			}
		}
	}
	
	static boolean connectedBfs() {
        // 1. 선택된 다리들로만 연결 리스트(인접 리스트) 생성
        List<Integer>[] adj = new ArrayList[mapCnt + 2];
        for(int i = 2; i <= mapCnt + 1; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < ladders.size(); i++) {
            if(dfs_visited[i]) {
                int u = ladders.get(i)[0];
                int v = ladders.get(i)[1];
                adj[u].add(v); // 양방향 연결
                adj[v].add(u);
            }
        }
        
        // 2. 2번 섬부터 시작해서 올바른 BFS 탐색
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visitIsland = new boolean[mapCnt + 2];
        
        q.add(2);
        visitIsland[2] = true;
        int connectedCount = 1; // 방문한 섬의 개수
        
        while(!q.isEmpty()) {
            int curr = q.poll();
            
            // 현재 섬과 "선택된 다리"로 이어진 이웃 섬들만 확인
            for(int next : adj[curr]) {
                if(!visitIsland[next]) {
                    visitIsland[next] = true;
                    connectedCount++; // 연결된 섬 카운트 증가
                    q.add(next);
                }
            }
        }
        
        // 방문한 섬의 개수가 총 섬의 개수와 같다면 모두 이어진 것!
        return connectedCount == mapCnt;
    }
	
	// 다리의 조합을 찾는 DFS
		// idx: 탐색할 다리 인덱스, count: 선택한 다리 개수, sum: 선택한 다리의 길이 합
		static void dfs(int idx, int count, int sum) {
			// 최적화: 이미 구한 최솟값보다 현재 합이 크거나 같으면 더 볼 필요 없음 (가지치기)
			if(sum >= minAns) return;
			
			// 종료 조건: 섬의 개수 - 1 개의 다리를 모두 골랐을 때
			if(count == mapCnt - 1) {
				// 고른 다리들로 모든 섬이 연결되는지 확인
				if(connectedBfs()) {
					minAns = Math.min(minAns, sum);
				}
				return;
			}
			
			// 모든 다리를 다 확인했으면 종료
			if(idx == ladders.size()) return;
			
			// 1. 현재 인덱스의 다리를 선택하는 경우
			dfs_visited[idx] = true;
			dfs(idx + 1, count + 1, sum + ladders.get(idx)[2]);
			
			// 2. 현재 인덱스의 다리를 선택하지 않는 경우
			dfs_visited[idx] = false;
			dfs(idx + 1, count, sum);
		}
}
