import java.util.Stack;

class Solution {
    public int solution(int[] order) {
        int answer = 0;

        Stack<Integer> sub = new Stack<>();
        sub.push(0);
        int idx = 0;

        for (int i = 0; i < order.length; i++) {

            while (idx < order[i])
                sub.push(idx++);
            
            if (order[i]==idx) {
                answer++; idx++; continue;
            }

            if (!sub.isEmpty() && sub.peek()==order[i]) {
                answer++; sub.pop();
            } else break;
        }
        return answer;
    }
}