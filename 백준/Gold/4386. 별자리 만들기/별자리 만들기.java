import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        stars = new double[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            stars[i][0] = Double.parseDouble(st.nextToken());
            stars[i][1] = Double.parseDouble(st.nextToken());
            for (int j = i-1; j >= 0; j--) {
                double[] edge = {j, i, getDistance(stars[i], stars[j])};
                edges.add(edge);
            }
        }

        Collections.sort(edges, (a, b)->Double.compare(a[2], b[2]));

        parents = new int[n];
        for (int i = 0; i < n; i++) parents[i] = i;

        int cnt = 0;
        double cost = 0;
        for (int i = 0; i < edges.size(); i++) {
            double[] curr = edges.get(i);
            if (union((int)curr[0], (int)curr[1])) {
                cost+=curr[2];
                cnt++;
            }
            if (cnt==n-1) break;
        }
        System.out.printf("%.2f", cost);
    }
    static int n;
    static double[][] stars;
    static int[] parents;
    static List<double[]> edges = new ArrayList<>();

    static double getDistance(double[] p1, double[] p2) {
        return Math.hypot(p1[0]-p2[0], p1[1]-p2[1]);
    }

    static boolean union(int x, int y) {

        int root_x = find(x);
        int root_y = find(y);

        if (root_x==root_y) return false;

        if (root_x < root_y) parents[root_y] = root_x;
        else parents[root_x] = root_y;

        return true;
    }

    static int find(int v) {
        if(parents[v]==v) return v;
        return parents[v] = find(parents[v]);
    }
}