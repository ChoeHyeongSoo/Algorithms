import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        // 동일 유저 신고 -> 1회 : Set
        Map<String, Set<String>> record = new HashMap<>();
        Map<String, Integer> idx = new HashMap<>();
        for (int i = 0; i < id_list.length; i++) idx.put(id_list[i], i);
        int[] answer = new int[id_list.length];
    
        for (String curr : report) {
            StringTokenizer st = new StringTokenizer(curr);   
            String from = st.nextToken(), to = st.nextToken();
            if (record.get(to)==null)
                record.put(to, new HashSet<>());
            record.get(to).add(from);
            // record.computeIfAbsent(to, k -> new HashSet<>()).add(something);
        }
        
        for (String curr : id_list) {
            if (record.get(curr)==null) continue;
            if (record.get(curr).size() < k) continue;
            for (String tmp : record.get(curr))
                answer[idx.get(tmp)]++;
        }
        
        return answer;
    }
}