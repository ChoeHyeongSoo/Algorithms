import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

class Solution {
    public String solution(String[] participant, String[] completion) {

        // 참가자, 완주자 맵에 저장  *동명이인 + 1
        HashMap<String, Integer> in = new HashMap<>();
        for (String name : participant) in.put(name, in.getOrDefault(name, 0) + 1);

        // 참가자 루프로 완주자와 밸류 불일치하면 반환
        for (String name : completion) {
            int is = in.get(name) - 1;
            if (is == 0) in.remove(name);
            else in.put(name, is);
        }

        return in.keySet().toString().replace("[", "").replace("]", "");
    }
}