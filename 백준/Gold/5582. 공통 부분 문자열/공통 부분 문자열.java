import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String A = br.readLine();
        String B = br.readLine();
        int a_length = A.length(), b_length = B.length();

//        if (a_length < b_length) {
//            String tmp = A;
//            A = B;
//            B = tmp;
//            a_length = A.length();
//            b_length = B.length();
//        }

        int max = 0;

        // diff 활용 슬라이딩 윈도우 : B 끝단이 A 앞단과 겹침 <= diff <= B 앞단이 A 끝단과 겹침
        for (int diff = -(a_length-1); diff <= b_length-1; diff++) {
            int curr = 0;

            for (int i = 0; i < a_length; i++) {
                int j = i + diff; // 두 배열을 1차원화 : 슬라이딩 윈도우

                if (j < 0 || j >= b_length) continue;

                if (A.charAt(i)==B.charAt(j)) {
                    curr++;
                    max = Math.max(max, curr);
                } else
                    curr = 0;
            }
        }
        System.out.println(max);
    }

}