package Algorithm.DivideConquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Gold5_1074_Z{  // Constraints: 0.5s, 512MB, 1<=N<=15, 0<=r,c<=2^N
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken()), c =Integer.parseInt(st.nextToken());
        cnt = 0; target = r * (1<<n) + c;   // 행렬의 사이즈 N은 (2^n == 1<<n)
        dc(0, 0,1<<n);
        System.out.println(cnt);
    }
    static int n, cnt, target;
    public static void dc(int r, int c,  int w) {

        if (r==target/(1<<n) && c==target%(1<<n)) return;   // 기저 조건

        if (target/(1<<n) < r+w/2) {            // 조건부 분할
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