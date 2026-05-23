import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        // Map<Integer, Integer> match = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int k = Integer.parseInt(st.nextToken());
            arr[i] = k;
            // match.put(k, match.getOrDefault(k, 0)+1);
        }
        
        Arrays.sort(arr);

        StringBuilder ans = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int k = Integer.parseInt(st.nextToken());
            ans.append(upperBound(arr, k) - lowerBound(arr, k)).append(" ");
            // ans.append(match.getOrDefault(k, 0)).append(" ");
        }

        System.out.println(ans);
    }
    
    public static int lowerBound(int[] arr, int key) {
        int l = 0;
        int r = arr.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (key <= arr[mid]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
    
    public static int upperBound(int[] arr, int key) {
        int l = 0;
        int r = arr.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (key < arr[mid]) { // lower랑 달리 = 포함 x
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}