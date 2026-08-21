import java.util.*;

class Solution {
    public int[] solution(int[] arr, int divisor) {

        ArrayList<Integer> ans = new ArrayList<>();

        for (int k : arr) if (k%divisor==0) ans.add(k);

        Collections.sort(ans);
        
        return ans.size() > 0 ? ans.stream().mapToInt(Integer::intValue).toArray() : new int[]{-1};
    }
}