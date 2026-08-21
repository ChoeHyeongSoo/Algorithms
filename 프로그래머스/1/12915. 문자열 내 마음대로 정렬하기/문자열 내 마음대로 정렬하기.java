import java.util.*;

class Solution {
    public String[] solution(String[] strings, int n) {

        // Arrays.sort(strings, Comparator.comparingInt((String s) -> s.charAt(n)).thenComparing(Comparator.naturalOrder()));
        Arrays.sort(strings, (a, b) -> {
            return a.charAt(n) != b.charAt(n) ? a.charAt(n) - b.charAt(n) : a.compareTo(b);
        });
        return strings;
    }
}