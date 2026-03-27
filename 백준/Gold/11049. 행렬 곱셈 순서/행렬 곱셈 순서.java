import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int[] sequence = new int[n+1];
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            sequence[i] = Integer.parseInt(st.nextToken());
            sequence[i+1] = Integer.parseInt(st.nextToken());
        }
        
        long[][] table = new long[n][n];
        for (int connected = 1; connected < n; connected++) {    // 행렬을 기준으로 연결 수를 테이블인덱스로 설정
            for (int i = 0; i < n - connected; i++) {            // i부터 connected개 연결한 것
                int j = i + connected;                           // 계산에 사용되는 값은 sequence 데이터이므로, k, j 위치 잘 확인
                table[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {    // 점화식과 3중 for문의 구조적 설계 다시 백지부터 해보기
                    long candidate = table[i][k] + sequence[i]*sequence[k+1]*sequence[j+1] + table[k+1][j];
                    table[i][j] = Math.min(table[i][j], candidate);
                }
            }
        }
        System.out.println(table[0][n-1]);
    }
    static int n;
}