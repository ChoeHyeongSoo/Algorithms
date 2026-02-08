package Algorithm.Greedy.D3;

import java.util.Scanner;

class D3_1289_MemoryRecovery {
    public static void main(String args[]) throws Exception
    {
        //System.setIn(new FileInputStream("res/Algorithm/Greedy/input_D3_1289_MemoryRecovery.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            String target = sc.next();
            int idx = target.indexOf('1');  // 첫 번째 1의 인덱스
            int ans = 0;
            if (idx != -1) {                // indexOf: 못찾으면 -1 반환
                ans++;                              // 첫 1부터 시작 - 카운트 하나 증가
                while (idx < target.length() - 1) {
                    char c = target.charAt(idx++);
                    char next = target.charAt(idx);
                    if (c != next) {                // 다르면 비트 플립
                        ans++;
                    }
                }
            }

            System.out.println("#" + test_case + " " + ans);
        }
    }

}