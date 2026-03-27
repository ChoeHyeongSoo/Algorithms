import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        chess = new String[n];
        for (int i = 0; i < n; i++) chess[i] = br.readLine();

        // 8x8 가능한 범위 내에서 비용 찾기
        min = Integer.MAX_VALUE;
        for (int i = 0; i <= n-8; i++) {
            for (int j = 0; j <= m-8; j++) {
                colors(i, j);
            }
        }
        System.out.println(min);
    }
    static int n, m, min;
    static String[] chess;
    static String[] white = {"WBWBWBWB", "BWBWBWBW", "WBWBWBWB", "BWBWBWBW", "WBWBWBWB", "BWBWBWBW", "WBWBWBWB", "BWBWBWBW"};
    static String[] black = {"BWBWBWBW", "WBWBWBWB", "BWBWBWBW", "WBWBWBWB", "BWBWBWBW", "WBWBWBWB", "BWBWBWBW", "WBWBWBWB"};
    public static void colors(int r, int c) { // 비용 계산 - 시작인덱스 기준으로

        int wht = 0, blk = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(chess[r+i].charAt(c+j)!=white[i].charAt(j)) wht++;
                if(chess[r+i].charAt(c+j)!=black[i].charAt(j)) blk++;
            }
        }
        min = Math.min(min, Math.min(wht, blk));
    }
}