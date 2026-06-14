class Solution {
    public int solution(int n, int[][] wires) {
        int answer = 99;

        parents = new int[n+1];
        for (int idx = 0; idx < n-1; idx++) {

            int curr = 0;
            for (int i = 1; i <= n; i++) parents[i] = i;

            for (int i = 0; i < n-1; i++) {

                if (idx==i) continue;   // 하나씩 끊으며 시뮬레이션

                union(wires[i][0], wires[i][1]);
            }
                                       // parents[i] x : find(i)로 경로 압축하며 검사
            for (int i = 1; i <= n; i++) if (find(i)==1) curr++; // 루트가 1인 그룹 카운트

            answer = Math.min(Math.abs(n - 2*curr), answer);    // 두 그룹의 차이 최소 갱신
        }

        return answer;
    }

    int[] parents;

    public void union(int x, int y) {

        int root_x = find(x), root_y = find(y);

        if (root_x == root_y) return;

        if (root_x < root_y) parents[root_y] = root_x;
        else parents[root_x] = root_y;
    }

    public int find(int v) {
        if (parents[v]==v) return v;
        return parents[v] = find(parents[v]);
    }
}