class Solution {
    public long solution(int n) {
        // DP : 1칸 전에서 오는 것과 2칸 전에서 오는 것 비
        long[] table = new long[n+1];
        table[0] = 1;
        for (int i = 1; i <= n; i++) {
            if (i>=2) table[i]+=table[i-2];
            table[i]+=table[i-1];
            table[i]%=1234567;
        }

        return table[n];
    }
}