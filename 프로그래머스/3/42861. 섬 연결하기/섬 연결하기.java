import java.util.Arrays;

class Solution {
    public int solution(int n, int[][] costs) {
        int answer = 0;

        parents = new int[n];

        for (int i = 0; i < n; i++) parents[i] = i;

        Arrays.sort(costs, (a, b) -> a[2] - b[2]);

        int cnt = 0;
        outer:
        while (true) {

            for (int i = 0; i < costs.length; i++) {

                int from = costs[i][0], to = costs[i][1], cost = costs[i][2];

                if (!union(from, to)) continue;

                cnt++;
                answer+=cost;
                if (cnt==n-1) break outer;
            }
        }

        return answer;
    }

    static int[] parents;

    public boolean union(int x, int y) {

        int root_x = find(x), root_y = find(y);

        if (root_x == root_y) return false;

        if (root_x < root_y)
            parents[root_y] = root_x;
        else parents[root_x] = root_y;

        return true;
    }

    public int find(int v) {
        if (parents[v]==v) return v;
        return parents[v] = find(parents[v]);
    }
}