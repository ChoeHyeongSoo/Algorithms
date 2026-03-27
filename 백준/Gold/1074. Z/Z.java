import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken()), c =Integer.parseInt(st.nextToken());
        cnt = 0; target = r * (1<<n) + c;
        dc(0, 0,1<<n);
        System.out.println(cnt);
    }
    static int n, cnt, target;
    public static void dc(int r, int c,  int w) {

        if (r==target/(1<<n) && c==target%(1<<n)) return;

        if (target/(1<<n) < r+w/2) {
            if (target%(1<<n) < c+w/2) {
                dc(r, c, w/2);
            } else {
                cnt+=(w/2*w/2);
                dc(r, c+w/2, w/2);
            }
        } else {
            if (target%(1<<n) < c+w/2) {
                cnt+=2*(w/2*w/2);
                dc(r+w/2, c, w/2);
            } else {
                cnt += 3 * (w / 2 * w / 2);
                dc(r + w / 2, c + w / 2, w / 2);
            }
        }
    }
}