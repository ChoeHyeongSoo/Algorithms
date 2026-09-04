import java.util.*;

class Solution {
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0, maxSizeOfOneArea = 0;
        int[] answer = new int[2];
        
        int[][] dir = {{0, 1, 0, -1}, {1, 0, -1, 0}};
        boolean[][] visit = new boolean[m][n];
        Deque<Integer> dq = new ArrayDeque<>();
        
        for (int i = 0; i < m*n; i++) {
            
            int r = i/n, c = i%n;
            int tmp = picture[r][c], cnt = 0;
            if (tmp==0) continue;
            if (visit[r][c]) continue;
            visit[r][c] = true;
            dq.add(r*n+c); cnt++;
            
            while (!dq.isEmpty()) {
                
                int curr = dq.poll();
                
                for (int d = 0; d < 4; d++) {
                    
                    int nr = curr/n + dir[0][d],
                        nc = curr%n + dir[1][d];
                    
                    if (nr < 0 || nc < 0 || nr >= m || nc >= n) continue;
                    if (visit[nr][nc]) continue;
                    if (picture[nr][nc]!=tmp) continue;
                    
                    visit[nr][nc] = true;
                    dq.add(nr*n + nc); cnt++;
                } 
            }
            
            maxSizeOfOneArea = Math.max(maxSizeOfOneArea, cnt);
            numberOfArea++;
        }
        

        answer[0] = numberOfArea; answer[1] = maxSizeOfOneArea;
        
        return answer;
    }
}