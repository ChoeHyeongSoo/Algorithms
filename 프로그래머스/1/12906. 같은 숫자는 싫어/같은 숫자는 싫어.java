import java.util.*;

public class Solution {
    public int[] solution(int []arr) {

        Deque<Integer> q = new ArrayDeque<>();

        for (int k : arr) if(q.isEmpty() || k!=q.peekLast()) q.offer(k);

        int[] answer = new int[q.size()];
        int idx = 0;
        while (!q.isEmpty()) answer[idx++] = q.poll();

        return answer;
    }
}