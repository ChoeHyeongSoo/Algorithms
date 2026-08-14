import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
    
        
        // 맵을 인덱싱 용도로 사용 : 이름 -> 번호로 연결되게
        Map<String, Integer> name2idx = new HashMap<>();
        
        for (int i = 0; i < friends.length; i++)
            name2idx.put(friends[i], i);
        
        // 1. 거래 장표 생성
        int[][] report = new int[friends.length][friends.length];
        
        // 2. 선물 지수 측정
        int[] present_index = new int[friends.length];
        
        StringTokenizer st;
        for (String line : gifts) {
            
            st = new StringTokenizer(line);
            
            int from = name2idx.get(st.nextToken()),
                  to = name2idx.get(st.nextToken());
            
            report[from][to]++;
            
            present_index[from]++; present_index[to]--; 
        }
        
        // 3. 정산
        int[] present = new int[friends.length];
        for (int i = 0; i < friends.length; i++) {
            for (int j = i+1; j < friends.length; j++) {
                
                if (report[i][j]==report[j][i]) { // 기록 x or equal
                    
                    if (present_index[i]==present_index[j]) continue;
                    
                    if (present_index[i] > present_index[j]) present[i]++;
                    else present[j]++;
                    
                } else {
                
                    if (report[i][j] > report[j][i]) present[i]++;
                    else present[j]++;
                
                }
            }
        }
        
        // 4. 최대 계산
        for (int k : present) answer = Math.max(answer, k);
        
        return answer;
    }
}