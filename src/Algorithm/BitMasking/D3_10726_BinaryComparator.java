package Algorithm.BitMasking;

import java.util.Scanner;

class D3_10726_BinaryComparator {
    /*
    정수 N, M 이 주어질 때, M의 이진수 표현의 마지막 N 비트가 모두 1로 켜져 있는지 아닌지를 판별하여 출력하라.
    [입력]
    - 첫 번째 줄에 테스트 케이스의 수 TC가 주어진다.
    - 이후 TC개의 테스트 케이스가 새 줄로 구분되어 주어진다.
    - 각 테스트 케이스는 다음과 같이 구성되었다.
    - 첫 번째 줄에 정수 N, M이 주어진다. (1 ≤ N ≤ 30 , 0 ≤ M ≤ 108)
     */
    public static void main(String args[]) throws Exception {
//        System.setIn(new FileInputStream("res/sample_input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            int m = sc.nextInt();

//            int check = (1<<n);
//            int compare = (1<<m);    // 확인
//            int len = 0, tmp = m;   // 비트 개수 구하기?

            // bitCount, ***toBinaryString 유용하게 할용 가능할듯!
            String ans = Classify_by_String(n, m);

            System.out.println("#" + test_case + " " + ans);

            Classify_by_Array(m, n);
        }

    }

    private static void Classify_by_Array(int m, int n) {
        int[] bit = new int[31];
        boolean is = true;
        int idx = 0;
        while (m > 0) {
            bit[idx] = m % 2;
            m /= 2;
            idx++;
        }
        for (int i = 0; i < n; i++) {
            if (bit[i] == 0) {
                is = false;
                break;
            }
        }

        String ans = is ? "ON" : "OFF";
    }

    // Integer.*의 bitCount, ***toBinaryString 유용하게 할용 가능할듯!
    private static String Classify_by_String(int n, int m) {
        StringBuilder n_bit = new StringBuilder();
//            n_bit.repeat("1",n);
        for (int i = 0; i < n; i++) {
            n_bit.append("1");
        }
        String target = Integer.toBinaryString(m);
//            String ans = target.substring(target.length()-n).equals(Integer.toBinaryString(1<<n)) ? "ON" : "OFF";
        if (target.length() > n)
            target = target.substring(target.length()- n);
        String ans = (target.equals(n_bit.toString())) ? "ON" : "OFF";
        return ans;
    }

}

/* Sample Input

5
4 0
4 30
4 47
5 31
5 62

 */