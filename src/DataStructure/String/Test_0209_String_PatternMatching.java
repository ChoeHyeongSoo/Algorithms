package DataStructure.String;

import java.io.InputStream;
import java.util.Scanner;

public class Test_0209_String_PatternMatching {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {

            String s = sc.next();
            int n = sc.nextInt();

            StringBuilder ans = new StringBuilder();
            StringBuilder except = new StringBuilder();

            for (int i = 0; i <= s.length() - n; i ++) {
//				int cnt = 0;
                int next_idx = -1;

                String temp = s.substring(i, i+n);
                if (ans.toString().contains(temp)) continue;
                if (except.toString().contains(temp)) continue;
//				cnt++;

                next_idx = s.substring(i+n).indexOf(temp);	// 한 번 더 포함되는지 체크
                if (next_idx == -1){except.append(temp); continue;}
//				cnt++;

                String check = s.substring(i+n + next_idx + n);
                if (check.contains(temp)) {except.append(temp); continue;}
//				if (cnt==2)
                ans.append(temp + " ");	// cnt 제거 가능, 단순 디버깅용 보조 장치
            }


            System.out.println("#" + tc + " " + ans);

//			String line = "abcedabcedabced";
//			String tt = line.substring(0, 4);
//			String kk = line.substring(4);
//
//			System.out.println(tt);
//			System.out.println(kk);
//			System.out.println(kk.indexOf(tt));
//			System.out.println(kk.lastIndexOf(tt));

        }

    }

}
