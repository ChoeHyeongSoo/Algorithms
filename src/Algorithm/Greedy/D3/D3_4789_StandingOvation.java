package Algorithm.Greedy.D3;

import java.util.Scanner;

class D3_4789_StandingOvation {
    /*
    각 테스트 케이스의 첫 번째 줄에는 ‘0’에서 ‘9’사이의 문자 만으로 이루어진 문자열이 주어진다. 이 문자열의 길이는 1001 이하이다.
    이 문자열의 첫 번째 글자가 의미하는 바는 기립 박수를 하고 있는 사람이 아무도 없을 때(0 명일 때) 기립 박수를 하는 사람의 수를 의미한다.
    그리고 i번째 글자가 의미하는 바는 기립 박수를 하고 있는 사람이 i-1명 이상일 때 기립 박수를 하는 사람의 수를 의미한다.
    가장 마지막 문자는 ‘0’이 아니다. (적어도 한 명의 관객이 있음을 의미한다.)
    */
    public static void main(String args[]) throws Exception
    {
        //System.setIn(new FileInputStream("res/input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            String line = sc.next();
            int now = line.charAt(0) - '0'; // 용감한 사람
            int ans = 0;
            for (int i = 1; i < line.length(); i++) {
                if (i > now) {
                    ans+=i-now;             // 부끄러운 사람 일으킬 고용인 수
                    now = i;
                }
                now+=line.charAt(i)-'0';    // 일어난 부끄러운 사람들

            }

            System.out.println("#" + test_case + " " + ans);
        }
    }

}
/* 샘플 인풋

4
11111
09
110011
1

 */