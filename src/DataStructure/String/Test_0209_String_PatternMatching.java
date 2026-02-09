package DataStructure.String;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test_0209_String_PatternMatching {
    /*
    주어진 문자열 s와 정수 n에 대해 S 안에서 길이가 N이면서 서로 겹치지 않도록 선택했을 때 정확히 두 개만 나타나는 패턴을 찾아라.
    [제약 조건]
    • 1 ≤ (S의 길이) ≤ 50
    • 1 ≤ N ≤ 50
    */
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {

            String s = sc.next();
            int n = sc.nextInt();

            StringBuilder ans = new StringBuilder();
            StringBuilder except = new StringBuilder();
            List<String> arr = new ArrayList<>();
            int ans_idx = 0;

            for (int i = 0; i <= s.length() - n; i++) {
//				int cnt = 0;
                int next_idx;

                String temp = s.substring(i, i + n);
//                if (ans.toString().contains(temp)) continue;
                if (arr.toString().contains(temp)) continue;
                if (except.toString().contains(temp)) continue;
//				  cnt++;

                next_idx = s.substring(i + n).indexOf(temp);    // 한 번 더 포함되는지 체크
                if (next_idx == -1) {
                    except.append(temp + " ");
                    continue;
                }
//				  cnt++;

                String check = s.substring(i + n + next_idx + n);
                if (check.contains(temp)) {
                    except.append(temp + " ");
                    continue;
                }
//				  if (cnt==2)
//                  ans.append(temp + " ");	// cnt 제거 가능, 단순 디버깅용 보조 장치
                arr.add(temp);
            }

            arr.sort(String::compareTo);    // 사전 순 정렬

            for (String in : arr) {
                ans.append(in + " ");   // 이 둘은 똑같은 결과
            }
            String ans2 = String.join(" ", arr);

//            System.out.println("#" + tc + " " + ans);
            System.out.println("#" + tc + " " + ans2);

        }

    }
}
