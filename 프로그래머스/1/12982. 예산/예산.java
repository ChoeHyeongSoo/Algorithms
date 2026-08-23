import java.util.*;

class Solution {
    public int solution(int[] d, int budget) {
        int answer = 0, curr = 0;
        
        Arrays.sort(d);
        for (int k : d) {
            if (curr + k > budget) break;
            curr+=k; answer++;
        }
        
        return answer;
    }
}