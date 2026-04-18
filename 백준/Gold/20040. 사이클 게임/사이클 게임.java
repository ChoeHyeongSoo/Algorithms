import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        int ans = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (!union(x, y)) {
                ans = i + 1;
                break;
            }
        }
        System.out.println(ans);
    }
    static int n, m;
    static int[] parent;

    static boolean union(int x, int y) {

        int root_x = find(x), root_y = find(y);

        if (root_x==root_y) return false;

        if (root_x < root_y) parent[root_y] = root_x;
        else parent[root_x] = root_y;

        return true;
    }

    static int find(int v) {
        if (parent[v]==v) return v;
        return parent[v]=find(parent[v]);
    }
}