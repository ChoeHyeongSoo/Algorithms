package DataStructure.Array;

import java.util.Scanner;

class D2_1979_WordFit {
    /*
    N X N 크기의 단어 퍼즐을 만들려고 한다. 입력으로 단어 퍼즐의 모양이 주어진다.
    주어진 퍼즐 모양에서 특정 길이 K를 갖는 단어가 들어갈 수 있는 자리의 수를 출력하는 프로그램을 작성하라.
    [제약 사항]
    1. N은 5 이상 15 이하의 정수이다. (5 ≤ N ≤ 15)
    2. K는 2 이상 N 이하의 정수이다. (2 ≤ K ≤ N)
    [입출력 특징]
    - 테스트 케이스의 첫 번째 줄에는 단어 퍼즐의 가로, 세로 길이 N 과, 단어의 길이 K 가 주어진다.
    - 테스트 케이스의 두 번째 줄부터 퍼즐의 모양이 2차원 정보로 주어진다.
    - 퍼즐의 각 셀 중, 흰색 부분은 1, 검은색 부분은 0
     */
    public static void main(String args[]) throws Exception
    {
        //System.setIn(new FileInputStream("res/input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int n = sc.nextInt();
            int k = sc.nextInt();   // 단어길이

            int[][] arr = new int[n][n];
            int[] row = new int[n];
            int[] col = new int[n];
            int tmp = 0, ans = 0;
            int continuous;

            for (int i = 0; i < n; i++) {           // 입력받으며 row에 대해 동시수행
                continuous = 0;
                tmp = 0;
                for (int j = 0; j < n; j++) {
                    arr[i][j] = sc.nextInt();
                    if (arr[i][j] == 1) {
                        tmp++;
                    }
                    else {                           // 빈 자리 -> 막힌 자리가 되었을 때, 가능여부 검사
                        continuous = tmp; tmp = 0;
                        if (continuous==k) ans++;
                    }
                    if (j==n-1) {                    // 끝에 도달했을 경우, 반드시 검사
                        continuous = tmp; tmp = 0;
                        if (continuous==k) ans++;
                    }
                }
            }

            for (int i = 0; i < n; i++) {           // col에 대해 한 번 더 수행
                continuous = 0;
                tmp = 0;
                for (int j = 0; j < n; j++) {
                    if (arr[j][i] == 1) {
                        tmp++;
                    }
                    else {
                        continuous = tmp; tmp = 0;
                        if (continuous==k) ans++;
                    }
                    if (j==n-1) {
                        continuous = tmp; tmp = 0;
                        if (continuous==k) ans++;
                    }
                }
            }

            System.out.println("#" + test_case + " " + ans);
        }
    }

}