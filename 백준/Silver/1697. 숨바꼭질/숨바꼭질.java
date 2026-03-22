import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        if (n >= k) System.out.println(n - k);
        else {
            boolean[] road = new boolean[100001];
            Deque<Integer> q = new ArrayDeque<>();
            Deque<Integer> next = new ArrayDeque<>();
            road[n] = true;
            q.offer(n);
            int sec = 0;
            while (!road[k]) {
                while (!q.isEmpty()) {

                    int curr = q.poll();

                    if (curr + 1 <= k && !road[curr+1]) {
                        road[curr + 1] = true;
                        next.offer(curr + 1);
                    }
                    if (curr - 1 > 0 && !road[curr-1]) {
                        road[curr - 1] = true;
                        next.offer(curr - 1);
                    }
                    if (curr * 2 <= 100001 && !road[curr*2]) {
                        road[curr * 2] = true;
                        next.offer(curr * 2);
                    }
                }
                while (!next.isEmpty()) q.offer(next.poll());
                sec++;
            }
            System.out.println(sec);
        }
    }
    static int n;
}