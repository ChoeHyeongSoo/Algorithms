import java.util.HashMap;
import java.util.Map;

class Solution {
    public String[] solution(String[] players, String[] callings) {

        Map<String, Integer> name_to_index = new HashMap<>();
        for (int i = 0; i < players.length; i++)
            name_to_index.put(players[i], i);

        // callings 순서대로 스왑 -> 탐색한 위치 앞과 위치 바꾸기
        for (String curr : callings) {

            // 맵에서 랭크 낮추고, 내 뒤 모든 랭크 증가 .. ?
            int idx = name_to_index.get(curr);
            String tmp = players[idx];
            players[idx] = players[idx-1];
            players[idx-1] = tmp;
            name_to_index.put(curr, idx-1);
            name_to_index.put(players[idx], idx);
        }

        return players;
    }
}