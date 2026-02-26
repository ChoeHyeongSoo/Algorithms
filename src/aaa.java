import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
    /*

     */
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            StringBuilder sb = new StringBuilder(br.readLine());

            // 순환 - 선형자료구조 : 덱
            ArrayList<Character> arr = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                sb.charAt(i);
            }


            // 순위-중복 금지 -> 세트에 저장해서 내림차순 정렬
            Set<Character> rank = new HashSet<>(); // 배열에 옮기며 정렬

            int w = n/4; // 16진수 길이
            for (int i = 0; i < 4; i++) {   // 4번만 반복

            }

            // 10진수 변환 : 정답

//            System.out.println("#" + test_case + " " + );
        }
    }
}

// 16->2진수 ; 각 자리 8^i * arr[i]
public static int convert() {

}