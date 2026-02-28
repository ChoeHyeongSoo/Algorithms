package Algorithm.Simulation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

class SW_Test_A_TreasureBoxPW {
    /*
    각 변에 16진수 숫자(0~F)가 적혀 있는 보물상자가 있다.
    보물 상자의 뚜껑은 시계방향으로 돌릴 수 있고, 한 번 돌릴 때마다 숫자가 시계방향으로 한 칸씩 회전한다.
    각 변에는 동일한 개수의 숫자가 있고, 시계방향 순으로 높은 자리 숫자에 해당하며 하나의 수를 나타낸
    보물상자에는 자물쇠가 걸려있는데, 이 자물쇠의 비밀번호는 보물 상자에 적힌 숫자로 만들 수 있는 모든 수 중, K번째로 큰 수를 10진 수로 만든 수이다.
    N개의 숫자가 입력으로 주어졌을 때, 보물상자의 비밀 번호를 출력하는 프로그램을 만들어보자.
    (서로 다른 회전 횟수에서 동일한 수가 중복으로 생성될 수 있다. 크기 순서를 셀 때 같은 수를 중복으로 세지 않도록 주의한다.)
    */
    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("res/Algorithm/Simulation/input_SW_Test_A_TreasureBoxPW.txt"));
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