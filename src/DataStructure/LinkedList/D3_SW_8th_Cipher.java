package DataStructure.LinkedList;

import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Scanner;

class D3_SW_8th_Cipher {
    /*
    1. I(삽입) x, y, s : 앞에서부터 x번째 암호문 바로 다음에 y개의 암호문을 삽입한다. s는 덧붙일 암호문들이다.[ ex) I 3 2 123152 487651 ]
    2. D(삭제) x, y : 앞에서부터 x번째 암호문 바로 다음부터 y개의 암호문을 삭제한다.[ ex) D 4 4 ]
    3. A(추가) y, s : 암호문 뭉치 맨 뒤에 y개의 암호문을 덧붙인다. s는 덧붙일 암호문들이다. [ ex) A 2 421257 796813 ]
    위의 규칙에 맞게 작성된 명령어를 나열하여 만든 문자열이 주어졌을 때, 암호문 뭉치를 수정하고, 수정된 결과의 처음 10개 암호문을 출력하는 프로그램을 작성하여라.

    [입력]
    첫 번째 줄 : 원본 암호문 뭉치 속 암호문의 개수 N ( 2000 ≤ N ≤ 4000 의 정수)
    두 번째 줄 : 원본 암호문 뭉치
    세 번째 줄 : 명령어의 개수 ( 250 ≤ M ≤ 500 의 정수)\
    네 번째 줄 : 명령어
     */
    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("DataStructure/LinkedList/input_D3_SW_8th_Cipher.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
//        T = sc.nextInt();
        T=10;

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();

            LinkedList<Integer> pw = new LinkedList<>();

            for (int i = 0; i < n; i++) {
                int value = sc.nextInt();
                pw.add(i, value);
            }

            int m = sc.nextInt();  // 주어진 명령 수
            int complete = 0;      // 반복 조건 카운트 변수
            int x = 0, y = 0;

            while (complete < m) {

                char order = sc.next().charAt(0);

                switch (order) {        // 각 명령 케이스 분할
                    case 'I':
                        x = sc.nextInt();
                        y = sc.nextInt();
                        for (int i = 0; i < y; i++) {
                            pw.add(x+i, sc.nextInt());
                        }
                        break;
                    case 'D':
                        x = sc.nextInt();
                        y = sc.nextInt();
                        for (int i = 0; i < y; i++) {
                            pw.remove(x);
                        }
                        break;
                    case 'A':
                        y = sc.nextInt();
                        for (int i = 0; i < y; i++) {
                            pw.addLast(sc.nextInt());
                        }
                        break;
                }

                complete++;
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                sb.append(pw.pop() + " ");
            }
            System.out.println("#" + test_case + " " + sb);
        }


    }
}