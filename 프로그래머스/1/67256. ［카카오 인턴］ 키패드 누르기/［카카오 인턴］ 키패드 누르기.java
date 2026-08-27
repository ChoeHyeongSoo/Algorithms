class Solution {
    public String solution(int[] numbers, String hand) {
        StringBuilder answer = new StringBuilder();
        
        // 1. 키패드 선언
        int[][] keypad = {
            {3,1}, // 0
            {0,0}, // 1
            {0,1}, // 2
            {0,2}, // 3
            {1,0}, // 4
            {1,1}, // 5
            {1,2}, // 6
            {2,0}, // 7
            {2,1}, // 8
            {2,2}, // 9
        };
        
        // l, r 상태 : 키패트를 4x3 배열로 취급;
        int[] left = {3, 0}, right = {3,2};
        
        for (int curr : numbers) {
            
            if (curr == 1 || curr == 4 || curr == 7) {
                answer.append('L');
                left = keypad[curr];
            } else if (curr == 3 || curr == 6 || curr == 9) {
                answer.append('R');
                right = keypad[curr];
            } else {
                
                int l_dist = Math.abs(keypad[curr][0] - left[0]) + Math.abs(keypad[curr][1] - left[1]),
                    r_dist = Math.abs(keypad[curr][0] - right[0]) + Math.abs(keypad[curr][1] - right[1]);
                
                if (l_dist < r_dist || (l_dist==r_dist && hand.equals("left"))) {
                    answer.append('L');
                    left = keypad[curr];
                } else if (l_dist > r_dist || (l_dist==r_dist && hand.equals("right"))) {
                    answer.append('R');
                    right = keypad[curr];
                }
            }
        }
        
        return answer.toString();
    }
}