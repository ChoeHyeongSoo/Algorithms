import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine(), b = br.readLine();
        int[][] table = new int[a.length()][b.length()];

        int init_i = a.indexOf(b.charAt(0)), init_j = b.indexOf(a.charAt(0));
        if (init_i>-1) for (int i = init_i; i < a.length(); i++) table[i][0] = 1;  // 초기화
        if (init_j>-1) for (int j = init_j; j < b.length(); j++) table[0][j] = 1;

        for (int i = 1; i < a.length(); i++) {
            char curr = a.charAt(i);
            for (int j = 1; j < b.length(); j++) {
                char cmp = b.charAt(j);
                if (curr==cmp) table[i][j] = table[i-1][j-1]+1; // 마지막 포인터들이 일치하면 두 문자열의 직전 서브 일치 + 1
                else table[i][j] = Math.max(table[i-1][j], table[i][j-1]);  // 불일치 : 둘 중 포인터 직전에서의 최대값
            }
        }
        System.out.println(table[a.length()-1][b.length()-1]);
    }
}