package DataStructure.String;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Scanner;

public class Test_0209_String_CharCounting {
    /*
    알파벳 소문자로 이루어진 문자열 N개가 공백을 사이에 두고 주어진다.
    모든 문자열을 통틀어 a부터 z까지의 문자 중 가장 많이 포함된 문자 중 사전 순으로 가장 앞서는 문자가 무엇인지 구하라.
    [제약 조건]
    • 1 ≤ N ≤ 9
    • 1 ≤ (문자열의 길이) ≤ 9
    • 각 테스트케이스별 실행 시간은 1초 이내
    */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {

            int n = sc.nextInt();
            char[] cnt = new char[26];  // 카운팅 배열 활용
            int max = -1;
            char ans = 0;

            for (int i = 0; i < n; i++) {
                String word = sc.next();
                for (int k = 0; k < word.length(); k++) {
                    char c = word.charAt(k);
                    cnt[c-'a']++;
                    if (max < cnt[c-'a']) { // cnt가 큰 경우는 max, ans 모두 변경
                        max = cnt[c-'a'];
                        ans = c;
                    }
                    else if (max == cnt[c-'a']) {   // 동일한 경우는 ans를 사전 순으로 앞선 것으로 변경
                        ans =  (ans < c ) ? ans : c ;
                    }
                }
            }

//            char ans = (char)(idx+'a');   // 더 복잡해지는 케이스에선 idx를 따로 두고 사용하는 것이 편리할듯.

            System.out.println("#" + tc + " " + ans);

        }
    }

}
