import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long[][] arr = new long[4][n]; // A에서 하나 선택, B에서 하나 선택 ... 입력 형태 변경
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                arr[j][i] = Long.parseLong(st.nextToken()); // [0][~] A [1][~] B
            }
        }

        // 조합 ? 2 - 2 로 쪼개서 투 포인터 : (A,B) - (C,D)
        // 1. AB합 생성 : a_i, b_i 모든 인덱스에 조합에 대해서 합계산 - 전체 flatten? : 시간초과
        long[] AB = transform(arr[0], arr[1], n);
        long[] CD = transform(arr[2], arr[3], n);

        Arrays.sort(AB); Arrays.sort(CD); // 이분탐색을 위한 정렬

        // 2. 두 배열로 합 계산하는 투 포인터 사용 : 중복되는 수가 존재할 수 있다. - 맵?
        long ans = 0;
        int l = 0, r = n*n-1;
        while(l<n*n && r>=0) {

            long curr = AB[l] + CD[r];

            if (curr == 0) {
                long ab = AB[l], cd = CD[r];
                long cnt_l = 0, cnt_r = 0;
                while (l < n*n && AB[l] == ab) {
                    l++; cnt_l++;
                }
                while (r>=0 && CD[r] == cd) {
                    r--; cnt_r++;
                }
                ans += (cnt_l * cnt_r);
            } else if (curr < 0) l++;
            else r--;
        }
        System.out.println(ans);
    }

    public static long[] transform(long[] x, long[] y, int n) {

        long[] sub = new long[n*n];
        int idx = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                sub[idx++] = x[i] + y[j];

        return sub;
    }
}