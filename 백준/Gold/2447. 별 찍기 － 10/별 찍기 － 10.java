import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        template = new char[n][n];
        ans = new StringBuilder();

        divide_conquer(0,0, n,false);

        for (char[] line : template) {
            for (char c : line) ans.append(c);
            ans.append('\n');
        }

        System.out.println(ans);
    }
    static int n;
    static StringBuilder ans;
    static char[][] template;

    public static void divide_conquer(int r, int c, int w, boolean center) {

        //중간은 " " / 팔방 "*"
        if (w==1) {
            template[r][c] = center ? ' ' : '*';  // w==3으로 한 줄씩 처리할 수 없을까 ?
            return;
        }

        divide_conquer(r, c, w/3, center);
        divide_conquer(r, c + w/3, w/3, center);
        divide_conquer(r, c + 2*w/3, w/3, center);
        divide_conquer(r + w/3, c, w/3, center);
        divide_conquer(r + w/3, c + w/3, w/3, true);
        divide_conquer(r + w/3, c + 2*w/3, w/3, center);
        divide_conquer(r + 2*w/3, c, w/3, center);
        divide_conquer(r + 2*w/3, c + w/3, w/3, center);
        divide_conquer(r + 2*w/3, c + 2*w/3, w/3, center);
    }
}