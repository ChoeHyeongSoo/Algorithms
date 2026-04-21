import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
 
class Solution {
    public static void main(String args[]) throws Exception {
        StringBuilder ans = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            ans.append("#").append(test_case).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            map = new int[n+2][n+2];
            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= n; j++)
                    map[i][j] = Integer.parseInt(st.nextToken());
            }
 
            // 행/열 방향 각각 연속성 테스트
            // 높이차이 x / 높이차이 1 - x만큼은 연속성 보장
            int cnt = 0;
            int[] tmp = new int[n+2];
            for (int each = 1; each <= n; each++) {
                if (check(map[each])) cnt++;
                for (int i = 1; i <= n; i++)
                    tmp[i] = map[i][each];
                if(check(tmp)) cnt++;
            }
            ans.append(cnt).append('\n');
        }
        System.out.print(ans);
    }
 
    private static boolean check(int[] each) {
        int curr = 1;
        for (int i = 1; i < n; i++) {
            int diff = each[i] - each[i+1];
 
            if(diff==0) curr++; // 연속성 추가
            else if(diff==1) {	// 다음 칸이 낮은 경우
                for (int j = 1; j <= x; j++) {
                    if (i+j>n || each[i+1] != each[i+j]) return false; // x만큼 길이 확보
                }	
                i += (x-1); // for문 증감 생각해서 i+x에 위치로 도달할 수 있도록 인덱스 조절
                curr = 0;
            }
            else if(diff==-1){	// 다음칸이 높은 경우 : 경사로 설치 가능 여부 체크
                if (curr<x) return false;
                curr=1; // 연속성에 i+1 포함시킨 상태로 초기화
            }
            else return false;
        }
        return true;
    }
 
    static int n, x;
    static int[][] map;
}