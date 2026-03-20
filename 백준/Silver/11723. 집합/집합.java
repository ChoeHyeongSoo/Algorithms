import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            String op = st.nextToken();
            if ("allempty".contains(op)) operation(op, 0);
            else operation(op, Integer.parseInt(st.nextToken()));
        }
        System.out.println(sb);
    }

    static int nums;    // 비트마스킹
    static StringBuilder sb;

    public static void operation(String s, int n) {
        switch (s) {
            case "add":
                nums |= (1 << n); // OR 연산 0 : 1 / 1: 1
                break;
            case "remove":
                nums &= ~(1 << n);  // AND NOT(n) 0: 0 / 1: 0
                break;
            case "check":   // 1인지 확인
                sb.append((nums & (1<<n))!=0?1:0).append('\n');
                break;
            case "toggle":  // XOR 연산 : 0: 1 / 1: 0
                nums ^= (1<<n);
                break;
            case "all":    // ex) 1<<3 - 1 = 1000(8)-1(0001) = 0111(7)
                nums = (1<<22)-1;
                break;
            case "empty":
                nums = 0;
                break;
        }
    }
}