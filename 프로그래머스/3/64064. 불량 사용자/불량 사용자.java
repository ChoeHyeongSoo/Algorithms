import java.util.HashSet;
import java.util.Set;

class Solution {
    public static int solution(String[] user_id, String[] banned_id) {

        // 순열 : 인덱스 맞춰보며 되는지 판단
        boolean[] check = new boolean[user_id.length];
        prevent_reputation = new HashSet<>();

        perm(0, check, user_id, banned_id);

        int answer = prevent_reputation.size();
        return answer;
    }

    static Set<Set<Integer>> prevent_reputation;

    public static void perm(int depth, boolean[] check, String[] user_id, String[] banned_id) {

        if (depth == banned_id.length) {    // 중복 방지

            Set<Integer> curr_case = new HashSet<>();

            for (int i = 0; i < check.length; i++)
                if (check[i]) curr_case.add(i);

            prevent_reputation.add(curr_case);

            return;
        }

        for (int i = 0; i < user_id.length; i++) {
            // 마킹 조건 일치 여부 판단 (*제거 일치)
            if (check[i]) continue;
            if (!isCorrect(user_id[i], banned_id[depth])) continue;

            // *rodo, *rodo || *r*do, **odo 등 동일 구조 중복은 어떻게 막지 ?
            // 순서 중요 x 순열 ? 어떻게 할지 생각하기 -> a c d / c a d는 동일한 것. : 세트에 인덱스 저장해서 같은 조합 있으면 패쓰 ?
            check[i] = true;
            perm(depth + 1, check, user_id, banned_id); // 방문처리 후 재귀, 복구
            check[i] = false;
        }
    }

    public static boolean isCorrect(String user, String banned) {

        if (user.length() != banned.length()) return false;

        for (int i = 0; i < user.length(); i++)
            if (user.charAt(i) != banned.charAt(i) && banned.charAt(i) != '*') return false;

        return true;
    }

}