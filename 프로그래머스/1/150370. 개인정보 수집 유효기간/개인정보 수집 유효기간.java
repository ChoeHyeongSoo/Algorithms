import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        // 모든 달 28일로 가정 : 월 환산 시에 일 비교 x
        StringTokenizer st = new StringTokenizer(today.replace(".", " "));
        int[] present = new int[3];
        for (int i = 0; i < 3; i++) present[i] = Integer.parseInt(st.nextToken());
        int val_today = present[0] * 10000 + present[1] * 100 + present[2];
        
        // 약관 지속기간 기록
        Map<String, Integer> duration = new HashMap<>();
        for (String line : terms) {
            st = new StringTokenizer(line);
            duration.put(st.nextToken(), Integer.parseInt(st.nextToken()));
        }
        
        // 결과 저장
        ArrayList<Integer> temp = new ArrayList<>();
        int[] compare = new int[3];
        for (int i = 0; i < privacies.length; i++) {
            st = new StringTokenizer(privacies[i].replace(".", " "));
            for (int j = 0; j < 3; j++) compare[j] = Integer.parseInt(st.nextToken());
            String tmp = st.nextToken();
            
            int month = compare[1] += duration.get(tmp);
            compare[0] += (month-1)/12;
            compare[1] = (month-1)%12 + 1;
            
            int val_compare = compare[0] * 10000 + compare[1] * 100 + compare[2];
            
            if (val_today >= val_compare) temp.add(i+1);
        }
        
        int[] answer = new int[temp.size()];
        for (int k = 0; k < temp.size(); k++)
            answer[k] = temp.get(k);
        
        return answer;
    }
}