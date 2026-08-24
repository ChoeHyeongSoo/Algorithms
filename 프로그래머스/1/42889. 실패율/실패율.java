import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        int user_cnt = stages.length;
        
        // 1. 실패율 계산
        Node[] report = new Node[N];
        int[] stage_cnt = new int[N+2];
        
        for (int k : stages) stage_cnt[k]++; // 스테이지 머무르는 카운트
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            sum += stage_cnt[i-1]; // 0으로 나눠지는 케이스 방지
            double fail_rate = (user_cnt - sum == 0) ? 0 : (double) stage_cnt[i] / (user_cnt - sum);
            report[i-1] = new Node(i, fail_rate);
        }
        
        Arrays.sort(report, (a, b) -> {
            if (a.fail_rate == b.fail_rate) return a.stage - b.stage;
            return Double.compare(b.fail_rate, a.fail_rate);
        }); // 정렬 조건 작성 : Double은 compare로 비교
        
        int[] answer = new int[N];
        for (int i = 0; i < N; i++) answer[i] = report[i].stage;
        return answer;
    }
}

class Node {
    int stage;
    double fail_rate;
    
    public Node(int stage, double fail_rate) {
        this.stage = stage;
        this.fail_rate = fail_rate;
    }
}