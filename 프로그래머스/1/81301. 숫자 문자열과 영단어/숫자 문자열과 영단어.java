import java.util.*;

class Solution {
    public int solution(String s) {
        
        HashMap<String, Integer> toNum = new HashMap<>();
        
        toNum.put("zero", 0);
        toNum.put("one", 1);
        toNum.put("two", 2);
        toNum.put("three", 3);
        toNum.put("four", 4);
        toNum.put("five", 5);
        toNum.put("six", 6);
        toNum.put("seven", 7);
        toNum.put("eight", 8);
        toNum.put("nine", 9);

        StringBuilder ans = new StringBuilder();
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c > '9') tmp.append(c);
            else ans.append(c);
            
            if (toNum.get(tmp.toString())!=null) {
                ans.append(toNum.get(tmp.toString()));
                tmp = new StringBuilder();
            }
        }
        
        return Integer.parseInt(ans.toString());
    }
}