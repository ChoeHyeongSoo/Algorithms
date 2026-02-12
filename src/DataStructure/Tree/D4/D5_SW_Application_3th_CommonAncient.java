package DataStructure.Tree.D4;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*
    이진 트리에서 임의의 두 정점의 가장 가까운 공통 조상을 찾고, 그 정점을 루트로 하는 서브 트리의 크기를 알아내는 프로그램을 작성하라.
    [입력]
    가장 첫 번째 줄에 테스트케이스의 수가 주어진다.
    각 케이스의 첫 번째 줄에는 정점의 개수 V(10 ≤ V ≤ 10000)와 간선의 개수 E, 공통 조상을 찾는 두 개의 정점 번호가 주어진다.
    각 케이스의 두 번째 줄에는 E개 간선이 나열된다. 간선은 항상 “부모 자식” 순서로 표기된다.
    위에서 예로 든 트리에서 정점 5와 8을 잇는 간선은 “5 8”로 표기된다.
    정점의 번호는 1부터 V까지의 정수이며, 루트 정점은 항상 1번이다.
 */
class Node_for_LCA {
    int idx;
    int parent;
    List<Integer> child = new ArrayList<>();
    int depth = 0;
    int subtree_size;

    public Node_for_LCA() {
    }
}

class D5_SW_Application_3th_CommonAncient {

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("res/DataStructure/Tree/input_D5_SW_Application_3th_CommonAncient.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();   // 정점
            int m = sc.nextInt();   // 간선

            int a = sc.nextInt();   // target 1
            int b = sc.nextInt();   // target 2

            Node_for_LCA[] tree = new Node_for_LCA[n + 2];
            for (int i = 1; i <= n; i++) {
                tree[i] = new Node_for_LCA();   // 자식노드 계산 위해서 미리 전부 생성
            }
            for (int i = 0; i < m; i++) {
                int p = sc.nextInt();
                int idx = sc.nextInt();
                tree[idx].parent = p;
                tree[p].child.add(idx);
            }

            int roots = set_depth(1, 1, tree);


            int ans = find_common(a, b, tree);

            System.out.println("#" + test_case + " " + ans + " " + tree[ans].subtree_size);
        }
    }

    private static int set_depth(int curr, int depth, Node_for_LCA[] tree) {
        tree[curr].depth = depth; // 현재 노드 깊이부터
        int size = 1; // 자기 자신 포함하는 깊이

        // 자식들을 방문하며 깊이를 1 늘려주고, 자식들의 서브트리 크기를 합
        for (int childIdx : tree[curr].child) {
            size+=set_depth(childIdx, depth + 1, tree);
        }

        tree[curr].subtree_size = size; // 계산된 서브트리 크기 저장
        return size;
    }

    private static int find_common(int a, int b, Node_for_LCA[] tree) {

        int x = tree[a].parent;
        int y = tree[b].parent;
        if (x==0||y==0) return 1;

        if (x == y || x == b) return x;
        if (a == y) return a;

        int result = 1;
        if (tree[x].depth > tree[y].depth)
            result = find_common(tree[x].parent, b, tree);
        else if (tree[x].depth < tree[y].depth)
            result = find_common(a, tree[y].parent, tree);
        else
            result = find_common(tree[x].parent, tree[y].parent, tree);

        return result;
    }

}
