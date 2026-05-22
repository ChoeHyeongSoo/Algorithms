import java.util.Arrays;

class Solution {
    public int solution(int[][] info, int n, int m) {
        // Top-Down DP : Backtracking - Threshold (n, m) - 불가능 존재
        // A가 최소가 되게 memoization
        INFO = info;
        table = new int[info.length+1][m+1];
        for (int[] r : table)
            Arrays.fill(r, -1);

        int ans = dp(info.length-1, m);
        return ans < n ? ans : -1; // n을 초과한 값을 가져오면 불가능
    }

    static final int INF = 100000;
    static int[][] INFO, table;

    public int dp(int i, int b) { // A에 대한 조건은 여기서 제외 

        if (b<=0) return INF; // A case: n, B case: m 이상 - INF 가지고 리턴

        // *기저조건 : 가지치기 없이 도달하면 여기서부터 값을 더하며 복귀
        if (i==-1) return 0; // 0부터 INFO[i][0] 값이 더해지므로, 0 반환

        if (table[i+1][b]!=-1) return table[i+1][b];

        int result = dp(i-1, b-INFO[i][1]);

        result = Math.min(result, dp(i-1, b) + INFO[i][0]); // A, B 선택에 대한 값 비교

        return table[i+1][b] = result;
    }
}