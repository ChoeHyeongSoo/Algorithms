import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        tree = new HashMap<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            char key = st.nextToken().charAt(0);
            char l = st.nextToken().charAt(0); char r = st.nextToken().charAt(0);
            tree.put(key, new char[]{l, r});
        }

        sb = new StringBuilder();
        preoder('A');
        sb.append('\n');
        inorder('A');
        sb.append('\n');
        postoder('A');
        sb.append('\n');

        System.out.println(sb);
    }

    static Map<Character, char[]> tree;
    static StringBuilder sb;
    
    public static void preoder(char v) {
        sb.append(v);
        if (tree.get(v)[0]!='.') preoder(tree.get(v)[0]);
        if (tree.get(v)[1]!='.') preoder(tree.get(v)[1]);
    }

    public static void inorder(char v) {
        if (tree.get(v)[0]!='.') inorder(tree.get(v)[0]);
        sb.append(v);
        if (tree.get(v)[1]!='.') inorder(tree.get(v)[1]);
    }

    public static void postoder(char v) {
        if (tree.get(v)[0]!='.') postoder(tree.get(v)[0]);
        if (tree.get(v)[1]!='.') postoder(tree.get(v)[1]);
        sb.append(v);
    }
}