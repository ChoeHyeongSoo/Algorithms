import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
    
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        edges = new Edge[e];
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i] = new Edge();
            edges[i].s = Integer.parseInt(st.nextToken());
            edges[i].e = Integer.parseInt(st.nextToken());
            edges[i].w = Integer.parseInt(st.nextToken());
        }

        root = new int[v+1];
        for (int i = 1; i <= v; i++) root[i] = i;

        Arrays.sort(edges, (a, b) -> Integer.compare(a.w, b.w));
        int weight = 0;
        for (Edge tmp : edges) {
            int x = tmp.s;
            int y = tmp.e;
            if (union(x, y)) weight+=tmp.w;
        }
        System.out.println(weight);
    }

    static Edge[] edges;
    static int[] root;

    public static boolean union(int x, int y) {

        x = find(x);
        y = find(y);

        if (x==y) return false;

        root[x] = y;
        return true;
    }

    public static int find(int v){
        if (root[v]==v) return v;
        return root[v] = find(root[v]);
    }
}

class Edge{
    int s, e, w;
}
