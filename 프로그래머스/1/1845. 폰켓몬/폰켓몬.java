import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        
        HashMap<Integer, Integer> mon = new HashMap<>();
        
        for (int curr : nums)
            mon.put(curr, mon.getOrDefault(curr, 0) + 1);
        
        return mon.size() > nums.length / 2 ? nums.length / 2 : mon.size();
    }
}