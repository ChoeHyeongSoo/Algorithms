package Algorithm.Implematation.D3;

import java.util.Scanner;

class D3_14555_FindBall {

    public static void main(String args[]) throws Exception {

        //  System.setIn(new FileInputStream("res/input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {

            String line = sc.next();

            int ans = 0;
            for (int i = 0; i < line.length(); i++) {   // (가 있으면 반드시 바로 다음 ), 공++
                char c = line.charAt(i);
                if (c=='(' && (line.charAt(i+1)=='|' || line.charAt(i+1)==')')) ans++;
                else if (c=='|' && i < line.length()-1 && line.charAt(i+1)==')') ans++;
            }                                           // |는 바로 뒤에 )가 오면 공++

            System.out.println("#" + test_case + " " + ans);

        }
    }
}