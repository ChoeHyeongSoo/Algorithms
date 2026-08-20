class Solution {
    public long solution(int a, int b) {
        long answer = 0;
        if (a <= b) while (a <= b) answer += a++;
        else while (a >= b) answer += a--;
        return answer;
    }
}