package DataStructure.Stack.D3;

import java.util.Scanner;
import java.util.Stack;

class D3_8931_Zero {

    public static void main(String args[]) throws Exception {

//         System.setIn(new FileInputStream("res/input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            int k = sc.nextInt();
            Stack<Integer> s = new Stack<>();
            for (int i = 0; i < k; i++) {
                int n = sc.nextInt();
                if (n==0) s.pop();
                else s.push(n);
            }
            int ans = 0;
            while (!s.isEmpty()) ans+=s.pop();

            System.out.println("#" + test_case + " " + ans);
        }

    }
}
