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
        int x = Integer.parseInt(br.readLine()), cnt = 0, sum = 0;
        Arrays.sort(arr);
        int left = 0, right = n-1;
        while (left<right) {
            sum = arr[left] + arr[right];
            if (sum < x) left++;
            else if (sum > x) right--;
            else {cnt++; left++; right--;}
        }
        System.out.println(cnt);
    }
}
