import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        root = new int[n+1];
        for (int i = 1; i <= n; i++) root[i] = i;   // 초기화

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++)
                if(Integer.parseInt(st.nextToken())==1) union(i, j);    // 연결되어 있다면 유니온
        }

        st = new StringTokenizer(br.readLine());
        int[] plan = new int[m];
        for (int i = 0; i < m; i++) plan[i] = Integer.parseInt(st.nextToken());

        String ans = "YES";
        for (int i = 1; i < m; i++)
            if (root[plan[i]]!=root[plan[i-1]]) {ans = "NO"; break;}    // 연결 x 종료: root에서 연결여부 확인

        System.out.println(ans);
    }

    static int[] root;
    static int n;
    static boolean[] visit;

    public static void union(int x, int y) {
        x = find(x); y = find(y);

        if (x==y) return;

        if (x < y) root[y] = x;
        else root[x] = y;
    }

    public static int find(int v){
        if (root[v]==v) return v;
        return root[v]=find(root[v]);
    }
}