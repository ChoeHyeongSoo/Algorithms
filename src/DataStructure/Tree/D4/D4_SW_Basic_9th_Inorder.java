package DataStructure.Tree.D4;

import java.util.Scanner;
import java.util.TreeMap;

public class D4_SW_Basic_9th_Inorder {

    public static void main(String args[]) throws Exception {
//        System.setIn(new FileInputStream("res/DataStructure/Tree/input_D4_SW_Basic_9th_Inorder.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
//        T = sc.nextInt();
        T = 10;

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();

            TreeMap<Integer, Character> tree = new TreeMap<>();

            for (int i = 0; i < n/2; i++) {
                int idx = sc.nextInt();
                char c = sc.next().charAt(0);
                tree.put(idx, c);
                int left = sc.nextInt();
                if (left==n) break;
                int right = sc.nextInt();
            }

            for (int i = 0; i < n-n/2; i++) {
                int idx = sc.nextInt();
                char  c = sc.next().charAt(0);
                tree.put(idx, c);
            }

            StringBuilder sb = new StringBuilder();
            inOrder(1, tree, sb);

            System.out.println("#" + test_case + " " + sb);
        }
    }

    public static void inOrder(int v, TreeMap tree, StringBuilder sb) {
        if (v> tree.size()) return;
        inOrder(v*2, tree, sb);
        sb.append(tree.get(v));
        inOrder(v*2+1, tree, sb);
    }

}
