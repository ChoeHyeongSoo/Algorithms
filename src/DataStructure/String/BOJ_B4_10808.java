package DataStructure.String;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class BOJ_B4_10808 { // 알파벳 개수 카운팅 문제

    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);

        String line = sc.next();

        int[] ans = new int[26];
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            ans[c-'a']++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            sb.append(ans[i] + " ");
        }

        System.out.println(sb.toString());
    }

}