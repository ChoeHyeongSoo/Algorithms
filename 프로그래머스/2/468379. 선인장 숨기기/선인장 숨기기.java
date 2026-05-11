import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public static int[] solution(int m, int n, int h, int w, int[][] drops) {

        // 1. 영역 세팅
        int[][] map = new int[m][n];
        for (int i = 0; i < drops.length; i++) {
            int r = drops[i][0];
            int c = drops[i][1];
            map[r][c] = i+1;
        }

        // 2. 행 기준 슬라이딩
        int[][] RowConv = new int[m][n-w+1]; // 가로 압축
        for (int i = 0; i < m; i++) {
            Deque<Integer> dq = new ArrayDeque<>(); // Pq처럼 구조화

            for (int j = 0; j < n; j++) {
                int curr = map[i][j] > 0 ? map[i][j] : INF;

                while (!dq.isEmpty()) { // 큐 모든 값 나갈 때까진 새로 들어온 값이 대표 가능
                    int rear_idx = dq.peekLast(); // 큐 끝의 인덱스
                    int rear_val = map[i][rear_idx] > 0 ? map[i][rear_idx] : INF;
                    // 큐 끝 값과 비교
                    if (rear_val >= curr) dq.pollLast();  // 현재값 이상이면 제거
                    else break; // 윈도우의 최소값이 대표
                }
                dq.offerLast(j); // 현재값 이상 다 비우고 끝에 삽입

                // + 그림을 통해서 덱과 인덱스 이해하기
                if (dq.peekFirst() < j-w+1) dq.pollFirst(); // 윈도우 범위 벗어나면 제거
                if (j-w+1 >= 0) // 윈도우 크기만큼 확보됐는가?
                    RowConv[i][j-w+1] = map[i][dq.peekFirst()] < INF ? map[i][dq.peekFirst()] : 0; // 대표값 설정
            }
        }

        // 3. 열 기준 슬라이딩
        int[][] ColConv = new int[m-h+1][n-w+1];
        for (int j = 0; j < n-w+1; j++) {
            Deque<Integer> dq = new ArrayDeque<>();

            for (int i = 0; i < m; i++) {
                int curr = RowConv[i][j] > 0 ? RowConv[i][j] : INF;

                while (!dq.isEmpty()) {
                    int rear_idx = dq.peekLast();
                    int rear_val = RowConv[rear_idx][j] > 0 ? RowConv[rear_idx][j] : INF;

                    if (rear_val >= curr) dq.pollLast(); // 현재값 이상이면 제거
                    else break; // 덱 마지막 값 현재보다 작으면 중단 (대표값 유지)
                }
                dq.offerLast(i);

                if (dq.peekFirst() < i-h+1) dq.pollFirst(); // 세로 범위 이탈 제거
                if (i-h+1 >= 0)         // 인덱스로 대표값 설정
                    ColConv[i-h+1][j] = RowConv[dq.peekFirst()][j] < INF ? RowConv[dq.peekFirst()][j] : 0;
            }
        }

        int[] answer = new int[2]; // x, y 좌표 저장
        max = 0;

        for (int i = 0; i < m-h+1; i++) {
            for (int j = 0; j < n-w+1; j++) {
                int tmp = ColConv[i][j];
                if (tmp==0) {
                    answer[0] = i;
                    answer[1] = j;
                    return answer;
                } else if (tmp > max) {
                    max = tmp;
                    answer[0] = i;
                    answer[1] = j;
                }
            }
        }

        return answer; // answer = {r, c};
    }

    static final int INF = Integer.MAX_VALUE;
    static int max;
}