import java.io.FileInputStream;
import java.util.Scanner;
import java.util.TreeMap;

class node{

    int idx, left, right;
    String value;
    boolean bottom = false;
    node left_node, right_node;

    public node(String value) {
        this.value = value;
    }
}

class tree_cal_ing {

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("res/input_Cal.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
//        T = sc.nextInt();
        T = 10;

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();

            TreeMap<Integer, Cal> tree = new TreeMap<>();

            for (int i = 0; i < n; i++) {
                int idx = sc.nextInt();
                String c = sc.next();
                String op = "*/+_";
                if (op.contains(c)) {
                    int left = sc.nextInt();
                    int right = sc.nextInt();
                } else {
                    int val = sc.nextInt();
                    tree.put(idx, new Cal(true, val));
                }
            }

            int ans = postOrder(1, tree);

            System.out.println("#" + test_case + " " + ans);
        }
    }

    public static int postOrder(int v, TreeMap<Integer, Cal> tree) {
        if (v < tree.size() && tree.get(v)!=null) {
            if (tree.get(v).bottom) return tree.get(v).value;
            int a = postOrder(v*2, tree);
            int b = postOrder(v*2+1, tree);
            switch (tree.get(v).operator) { // cond :
                case '+':
                    tree.get(v).value = a + b;
                    tree.get(v).bottom = true;
                    break;
                case '-':
                    tree.get(v).value = a - b;
                    tree.get(v).bottom = true;
                    break;
                case '*':
                    tree.get(v).value = a * b;
                    tree.get(v).bottom = true;
                    break;
                case '/':
                    tree.get(v).value = a / b;
                    tree.get(v).bottom = true;
                    break;

            }
            return tree.get(v).value;
        }
        return 0;
    }

}