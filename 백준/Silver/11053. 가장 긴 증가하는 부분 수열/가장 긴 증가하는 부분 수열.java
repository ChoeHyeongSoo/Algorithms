import java.awt.image.Kernel;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.jar.JarEntry;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
        int[] table = new int[n+1];
        table[0] = 1;
        for (int i = 0; i < n; i++) { // i 앞까지의 값들 중에서 조건 만족하는 케이스에서 제일 큰값 + 1
            int max = 0, idx = 0;
            for (int j = 0; j < i; j++) {
                if (arr[j] >= arr[i]) continue;
                max = Math.max(max, table[j]);
//                if (max < table[j]) {
//                    max = table[j];
//                }
            }
            table[i] = max + 1;
        }
        System.out.println(Arrays.stream(table).max().getAsInt());
    }
    static int n;
}