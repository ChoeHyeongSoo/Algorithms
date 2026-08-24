import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        
        // 1. 인덱스 기록
        int[] available = new int[board[0].length];
        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[j][i]==0) continue;
                available[i] = j; break;
            }
        }
        
        // 2. move 수행
        Stack<Integer> s = new Stack<>();
        s.push(0);
        for (int k : moves) {
            if (available[k-1]==board.length) continue;
            int curr = board[available[k-1]++][k-1];
            if (s.peek()!=curr) s.push(curr);
            else {
                s.pop(); answer+=2;
            }
        }
        
        return answer;
    }
}