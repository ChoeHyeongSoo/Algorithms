package Algorithm.BruteForce;

import java.util.Scanner;

class D3_2817_SubsetSum {
    /*
    A1, A2, ... , AN의 N개의 자연수가 주어졌을 때, 최소 1개 이상의 수를 선택하여 그 합이 K가 되는 경우의 수를 구하라
    각 테스트 케이스의 첫 번째 줄에는 2개의 자연수 N(1 ≤ N ≤ 20)과 K(1 ≤ K ≤ 1000)가 주어진다.
    두 번째 줄에는 N개의 자연수 수열 A, 수열의 원소인 N개의 자연수는 공백을 사이에 두고 주어지며, 1 이상 100 이하임이 보장
    */
    public static void main(String args[]) throws Exception {
//        System.setIn(new FileInputStream("res/sample_input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            int k = sc.nextInt();

            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            int cnt_for_loop = 0;
            boolean[] subset = new boolean[n];

            for (int i = 0; i < (1 << n); i++) {    // 길이 n의 비트 생성
                int tmp = 0;
                for (int j = 0; j < n; j++) {       // 0(00) 1(01) 2(10) 3(11)
                    int debug = (1<<j);
                    if ((i & (1 << j)) != 0) {      // 0000 0001 0010 0011 0100
                        tmp += arr[j];              // &로 and조건이 되는 j를 찾는 것이니 boolean[] {f, f, t, f} 형태
                    }
                }
                if (tmp==k) cnt_for_loop++;
            }

            int cnt_recursive = recursive(n, 0, k, arr, subset);

            System.out.println("#" + test_case + " " + cnt_for_loop);
            System.out.println("#" + test_case + " " + cnt_recursive);
        }
    }

    public static int recursive(int n, int depth, int k, int[] arr, boolean[] subset) {
        int result = 0;
        int cnt = 0, tmp = 0;            //  카운트 변수, 비교 변수 초기화
        for (int i = 0; i < n; i++) {
            if (subset[i]) tmp += arr[i]; // 활성화된 부분집합 합
        }
        if (tmp == k) cnt++;

        if (n == depth) return cnt;       // 재귀문-반복문은 항상 변수 초기화, 종료조건 위치 및 반환값 유의***


        subset[depth] = true;
        result += recursive(n, depth + 1, k, arr, subset);
        subset[depth] = false;
        result += recursive(n, depth + 1, k, arr, subset);
        return result;
    }

}
/*
1
4 3
1 2 1 2

#1 4
 */