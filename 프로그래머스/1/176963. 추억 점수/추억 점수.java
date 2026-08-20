import java.util.*;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
        
        HashMap<String, Integer> name2score = new HashMap<>();
        
        for (int i = 0; i < name.length; i++)
            name2score.put(name[i], yearning[i]);
        
        for (int i = 0; i < photo.length; i++)
            for (String who : photo[i])
                answer[i] += name2score.getOrDefault(who, 0);
            
        
        return answer;
    }
}