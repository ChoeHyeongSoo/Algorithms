import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        while (true) {
            String s = br.readLine(); if (s.equals("0")) break;
            StringBuilder sb = new StringBuilder(s).reverse();
            ans.append(s.contentEquals(sb)?"yes":"no").append('\n');
        }
        System.out.println(ans);
    }
    static int n;
}