import java.net.*;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        StringBuilder ans = new StringBuilder();
        int min = Integer.MAX_VALUE, one = 0, two = 0;
        for_loop:
        for (int i = 0; i < n-1; i++) { // 하나 고정
            int left = i+1, right = n-1;
            while (left<=right) {           // 이분 탐색으로 두 번째 용액 값 탐색
                int mid = (left+right)/2;
                int curr = arr[i]+arr[mid];

                if (Math.abs(curr) < Math.abs(min)) {   // 작은 경우에 갱신
                    min = curr; one = arr[i]; two = arr[mid];
                }

                if (curr==0) {  // left, right는 mid값을 0으로 만들기 위한 index
                    one = arr[i]; two = arr[mid];
                    break for_loop;
                } else if (curr > 0) right = mid-1;
                else left = mid+1;
            }
        }
        ans.append(one).append(" ").append(two);
        System.out.println(ans);
    }
}