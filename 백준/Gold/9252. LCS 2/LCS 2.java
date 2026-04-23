import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String first = br.readLine();
        String second = br.readLine();

        int[][] table = new int[first.length()+1][second.length()+1];

        for (int i = 1; i <= first.length(); i++) {
            for (int j = 1; j <= second.length(); j++) {

                if (first.charAt(i-1)==second.charAt(j-1)) // 현재 인덱스 일치 : 이전값 + 1
                    table[i][j] = table[i - 1][j - 1] + 1;
                else
                    table[i][j] = Math.max(table[i-1][j], table[i][j-1]); // 이전값 중 최대 사용

            }
        }

        StringBuilder sb = new StringBuilder();
        int i = first.length(), j = second.length();
        while (i > 0 && j > 0) {
            if (first.charAt(i - 1) == second.charAt(j - 1)) {
                sb.append(first.charAt(i - 1));
                i--; j--;
            } else if (table[i - 1][j] >= table[i][j - 1]) // 더 긴 곳으로 이동
                i--;
            else       // table[i][j]는 table[i-1][j], table[i][j-1] 중 긴 곳에서 왔기 때문에 인덱스 찾기 
                j--;
        }

        System.out.println(sb.length()==0? 0 : String.valueOf(sb.length()) + '\n' + sb.reverse());

    }

}

