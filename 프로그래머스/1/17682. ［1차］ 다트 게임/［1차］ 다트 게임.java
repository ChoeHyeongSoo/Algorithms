import java.util.*;

class Solution {
    public int solution(String dartResult) {
        int answer = 0;
        
        Stack<Integer> num = new Stack<>(), result = new Stack<>();
        
        for (int i = 0; i < dartResult.length(); i++) {
            
            char c = dartResult.charAt(i);
            
            if (c >= '0' && c <= '9') num.push(c-'0');   
            else if (c >= 'D' && c <= 'T') {
                int tmp = (num.size() > 1) ? num.pop() + num.pop() * 10 : num.pop();
                if (c=='S') result.push(tmp);
                if (c=='D') result.push((int)Math.pow(tmp, 2));
                if (c=='T') result.push((int)Math.pow(tmp, 3));
            } else {
                if (c=='*') {
                    num.push(result.pop()*2);
                    if (result.size() > 0) num.push(result.pop()*2);
                    while (!num.isEmpty()) result.push(num.pop());
                } else result.push(result.pop() * (-1));
            }
        }
        
        while(!result.isEmpty()) answer += result.pop();
        
        return answer;
    }
}