import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        blue = 0; white = 0;
        divide_conquer(0, 0, n);
        StringBuilder sb = new StringBuilder();
        sb.append(white).append('\n').append(blue);
        System.out.println(sb);
    }
    static int[][] map;
    static int blue, white;
    public static void divide_conquer(int r, int c, int w) {

        int now = 0;
        for (int i = r; i < r+w; i++)
            for (int j = c; j < c+w; j++)
                if (map[i][j]==1) now++;

        if (now==0) { white++; return; }
        else if (now==w*w){ blue++; return; }

        divide_conquer(r, c, w/2);
        divide_conquer(r, c+w/2, w/2);
        divide_conquer(r+w/2, c, w/2);
        divide_conquer(r+w/2, c+w/2, w/2);
    }
}