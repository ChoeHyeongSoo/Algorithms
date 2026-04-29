class Solution {
    public static int lengthOfLongestSubstring(String s) {

        int[] include = new int[128];
        for (int i = 0; i < 128; i++) include[i] = -1;

        int max = 0, left = 0;

        for (int right = 0; right < s.length(); right++) {

            char c = s.charAt(right);

            if (include[c] >= left)
                left = include[c] + 1; // 카운팅 시작할 인덱스 설정 장치

            include[c] = right;

            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}