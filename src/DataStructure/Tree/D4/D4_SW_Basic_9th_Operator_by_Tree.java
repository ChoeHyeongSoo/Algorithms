package DataStructure.Tree.D4;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.TreeMap;

class node_Operator {
//    int idx;
    int left, right;
    String operator;
    int value;
    boolean bottom = false;
//    DataStructure.Tree.D4.node_Operator left_node, right_node;

    public node_Operator(int left, int right, String operator) {
//        this.idx = idx;
        this.left = left;
        this.right = right;
        this.operator = operator;
//        this.left_node = null;
//        this.right_node = null;
    }

    public node_Operator(int value, boolean bottom) {
//        this.idx = idx;
        this.value = value;
        this.bottom = bottom;
    }
}

class D4_SW_Basic_9th_Operator_by_Tree {

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("res/DataStructure/Tree/input_D4_SW_Basic_9th_Operator_by_Tree.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
//        T = sc.nextInt();
        T = 10;

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();

            // DataStructure.Tree.D4.node_Operator[] 형태로 사용하는 게 가장 효율적일듯
            TreeMap<Integer, node_Operator> tree = new TreeMap();    // 정렬이 필요하거나 키값 불규칙할 때 사용

            for (int i = 0; i < n; i++) {
                int idx = sc.nextInt();
                String c = sc.next();
                String op = "*/+-";     // 연산자 일치여부 확인

                if (op.contains(c)) {           // 연산자일 경우 좌, 우 읽어와서 노드 생성
                    int left = sc.nextInt();
                    int right = sc.nextInt();
                    tree.put(idx, new node_Operator(left, right, c));
                } else {                        // 피연산자일 경우 값만 대입
                    tree.put(idx, new node_Operator(Integer.parseInt(c), true));
                }
            }

            int ans = postOrder(1, tree);

            System.out.println("#" + test_case + " " + ans);
        }
    }

    public static int postOrder(int v, TreeMap<Integer, node_Operator> tree) {
        if (v <= tree.size() && tree.get(v)!=null) {
            if (tree.get(v).bottom) return tree.get(v).value;   // 바닥(피연산자)인 거 확인 - 값 반환
            int a = postOrder(tree.get(v).left, tree);
            int b = postOrder(tree.get(v).right, tree);
            switch (tree.get(v).operator.charAt(0)) {
                case '+':
                    tree.get(v).value = a + b;
//                    tree.get(v).bottom = true;    // 굳이 안 해도 재귀 빠져나가며 값만 반환
                    break;
                case '-':
                    tree.get(v).value = a - b;
//                    tree.get(v).bottom = true;
                    break;
                case '*':
                    tree.get(v).value = a * b;
//                    tree.get(v).bottom = true;
                    break;
                case '/':
                    tree.get(v).value = a / b;
//                    tree.get(v).bottom = true;
                    break;

            }
            return tree.get(v).value;
        }
        return 0;
    }

}