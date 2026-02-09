package DataStructure.Array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class D3_7102_CardGame {
    /*
    각 카드 세트는 1번 카드부터 N번 카드, 1번 카드부터 M번 카드로 구성되어 있다.
    1번 카드와 1번 카드를 골랐다면 카드에 적힌 숫자의 합은 2가 될 것이고, N번 카드와 M번 카드를 골랐다면 카드에 적힌 숫자의 합은 N+M이 될 것이다.
    문득 준홍이는 각 카드 세트에서 카드를 한 장씩 골라서 카드에 적힌 숫자를 합한 결과 중, 등장할 확률이 가장 높은 숫자는 어떤 숫자일지 궁금해졌다.
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
            int m = sc.nextInt();

            int[] cnt = new int[n+m+1]; // 카운팅 배열 활용

            int max = 0;
            List<Integer> ans = new LinkedList<>();
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    cnt[i+j]++;
                    max = Math.max(max, cnt[i+j]);
                }
            }

            for (int i = 2; i <= n+m; i++) {
                if (cnt[i]==max) ans.add(i);    // 확률이 가장 높은 값들은 정답 리스트에 추가
            }

            Arrays.sort(ans.toArray());

            StringBuilder sb = new StringBuilder();
            for (int val : ans) {
                sb.append(val + " ");
            }

            System.out.println("#" + test_case + " " + sb.toString());
        }
    }

}