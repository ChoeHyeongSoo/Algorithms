package Algorithm.BinarySearch.D3;

import java.util.Scanner;

class D3_25837_SumAndProduct {

    public static void main(String args[]) throws Exception {

//         System.setIn(new FileInputStream("res/input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {

            long s = sc.nextLong();     // Error 1: 10^12 : int 타입 사용하면 런타임 에러 발생
            long p = sc.nextLong();
            String ans = "No";

            // Logic 1. n or m == 1, s > p / n and m == 2, s == p else s < p
            while_loop:
            while (true) {      // n, m 증가하며 찾을 거 아니면 loop 제거하기
                if (s > p) {
                    if (s == p + 1) ans = "Yes";
                    break while_loop;    }
                else if (s == p) {
                    if (s == 4)
                        ans = "Yes";
                    break while_loop;
                }

////            1. 기본 반복 형태 : 횟수로 하거나, while을 통해 a > 1 or b < n도 가능
//                long a = 0, b = 0;                 // s 10 p 100  s/2 탐색 -> Odds: s/2, s/2+1)
//                if (s%2==0)  {a = s/2; b = s/2;}
//                else {a = s/2; b = s/2+1;}
//                long multiple = 0;
//                for (int i = 0; i < s/2 ; i++) {    // Error 2: 반복횟수 큰 케이스 존재, 시간초과
//                    multiple = a * b;
//                    if (multiple == p) {ans = "Yes"; break while_loop;}
//                    a--;
//                    b++;
//                }

//            1. 단순 반복문 : Math.sqrt 포함 안 해서 1케이스 fail
                for (long i = 2; i <= Math.sqrt(p); i++) {
                    if (p%i==0 && i + p/i == s) {ans = "Yes"; break while_loop;}
                }

//            2. 재귀함수 구현 : dfs -> 범위설정 mid 계산 오류로 런타임에러 다섯 번
                boolean find = optimized_cal(2, (long)Math.sqrt(p), p, s);
                if (find) ans = "Yes";

                break;
            }

            System.out.println(ans);
        }

    }

    public static boolean optimized_cal (long min, long max, long p, long s) {
        long mid = min + (max - min) / 2;
        if (min > max) return false;
        if (p%mid == 0) {
            long compare =  mid + p/mid;
            if(compare == s) return true;}
        double compare_d = (double)mid + (double)p/mid; // double없이 long타입으로 했는데, 그렇게 하면 걸러내지 못하는 케이스 발생
        if (compare_d > s) {return optimized_cal(mid+1, max, p, s);}     // n+1과 p 사이
        else {return optimized_cal(min, mid-1, p, s);}
    }

}
