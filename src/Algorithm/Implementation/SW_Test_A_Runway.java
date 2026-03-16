package Algorithm.Implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class SW_Test_A_Runway {
    /*

     */
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

            if(diff==0) curr++;
            else if(diff==1) {
                for (int j = 1; j <= x; j++) {
                    if (i+j>n || each[i+1] != each[i+j]) return false;
                }
                i += (x-1); // 인덱스 이동: 슬로프 설치한 이후 - i+=x; / curr=1;로 하면 비교 위치가 달라진다
                curr = 0;   // 인덱스부터 다시 curr++하도록 설정
            }
            else if(diff==-1){
                if (curr<x) return false;
                curr=1; // 이전까지의 길이니 다시 curr 1로 카운트하고 idx 증가
            }
            else return false;
        }
        return true;
    }

    static int n, x;
    static int[][] map;
}