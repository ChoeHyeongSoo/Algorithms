class Solution {
    public long solution(int a, int b) {
        long min = Math.min(a, b), max = Math.max(a, b);
        return (min + max) * (max - min + 1) / 2;
    }
}