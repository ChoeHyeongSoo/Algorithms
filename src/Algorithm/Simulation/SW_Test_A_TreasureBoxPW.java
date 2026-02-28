package Algorithm.Simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class SW_Test_A_TreasureBoxPW {
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

            // 순환 - 선형자료구조 : 덱/LinkList-ArrayList : 성능비교
            LinkedList<Character> arr = new LinkedList<>();
            for (int i = 0; i < n; i++)
                arr.add(sb.charAt(i));
            // 순위-중복 금지 -> 세트에 저장 - 정렬
            Set<String> pw = new HashSet<>(); // 배열에 옮기며 정렬
            int w = n/4; // pw 길이
            save(arr, pw, w);
            for (int i = 0; i < w; i++)  // w번 회전
                rotate_save(arr, pw, w);

            // 정렬 및 k번째 반환
            int ans = get_pw(pw, k);
            // 10진수 변환 : 정답

            System.out.println("#" + test_case + " " + ans);
        }
    }

    // 회전
    public static void rotate_save(LinkedList<Character> arr, Set<String> pw, int w) {
        arr.addFirst(arr.getLast());
        arr.removeLast();
        save(arr, pw, w);
    }

    // 비밀번호 저장
    public static void save(LinkedList<Character> arr, Set<String> pw, int w) {
        for (int i = 0; i < 4; i++) {
            StringBuilder sb = new StringBuilder();
            for (int idx = 0; idx < w; idx++)
                sb.append(arr.get(w*i+idx));
            pw.add(sb.toString());
        }
    }

    // 정렬 : 각 자리 char 크기 비교 / 비교해서 크면 앞쪽으로, 같으면 다음 자리 비교
    public static int get_pw(Set<String> pw, int k) {
        int[] rank = new int[pw.size()];
        convert(pw, rank);
        Arrays.sort(rank);

        k = pw.size() - k;
        return rank[k];
    }

    // 16->2진수 ; 각 자리 8^i * arr[i]
    public static void convert(Set<String> pw, int[] arr) {
        // c - '0', A~F는 - 'A' +10 : "ABCEF".contains(c) <-valueOf(c)? <- D를 빼먹어서 2시간동안 디버깅..
        int idx = 0;
        for (String s : pw) {
            int tmp = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if ("ABCDEF".contains(String.valueOf(c)))
                    tmp += (c - 'A' + 10)*(1<<(4*(s.length()-1-i)));
                else tmp += (c - '0')*(1<<(4*(s.length()-1-i)));
            }
            arr[idx++] = tmp;
        }
    }
}