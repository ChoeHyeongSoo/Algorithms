import java.util.HashMap;

class Solution {
    public String solution(String[] survey, int[] choices) {

        score = new HashMap<>();

        update(survey, choices);

        StringBuilder ans = new StringBuilder();

        int r = score.getOrDefault('R', 0),
                t = score.getOrDefault('T', 0),
                c = score.getOrDefault('C', 0),
                f = score.getOrDefault('F', 0),
                j = score.getOrDefault('J', 0),
                m = score.getOrDefault('M', 0),
                a = score.getOrDefault('A', 0),
                n = score.getOrDefault('N', 0);

        ans.append(r >= t ? 'R' : 'T')
                .append(c >= f ? 'C' : 'F')
                .append(j >= m ? 'J' : 'M')
                .append(a >= n ? 'A' : 'N');

        return ans.toString();
    }

    static HashMap<Character, Integer> score;

    public void update(String[] c, int[] p) {
        for (int i = 0; i < c.length; i++) {
            char l = c[i].charAt(0), r = c[i].charAt(1);

            if (p[i] < 4)
                score.put(l, score.getOrDefault(l, 0) + 4 - p[i]);
            else if (p[i] > 4)
                score.put(r, score.getOrDefault(r, 0) + p[i] - 4);
        }
    }
}