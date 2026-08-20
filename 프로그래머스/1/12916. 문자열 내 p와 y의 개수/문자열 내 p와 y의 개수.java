class Solution {
    boolean solution(String s) {
        
        s = s.toLowerCase();
        int pCount = 0, yCount = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c=='p') pCount++;
            else if (c=='y') yCount++;
        }
        
        return pCount == yCount;
        
    }
}

// 길이를 비교하는 방법
// int pCount = lower.length() - lower.replace("p", "").length();
// int yCount = lower.length() - lower.replace("y", "").length();
        
// 스트림 활용 방법
// long pCount = lower.chars().filter(c -> c == 'p').count();
// long yCount = lower.chars().filter(c -> c == 'y').count();