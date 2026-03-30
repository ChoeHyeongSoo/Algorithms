import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] road = new int[100001];
        Arrays.fill(road, Integer.MAX_VALUE);

        Deque<Integer> q = new ArrayDeque<>();
        road[n] = 0; q.offer(n);

        while (!q.isEmpty()) {

            int curr = q.poll();
            if (curr==k) break;

            if (curr>0 && curr*2 <= 100000 && road[curr*2] > road[curr]) {  // *2 logic
                road[curr*2] = road[curr];
                q.addFirst(curr*2);
            }
            if (curr+1 <= 100000 && road[curr+1] > road[curr]+1) {  // +1 logic
                road[curr+1] = road[curr]+1;
                q.addLast(curr+1);
            }
            if (curr-1 >= 0 && road[curr-1] > road[curr]+1) {   // -1 logic
                road[curr-1] = road[curr]+1;
                q.addLast(curr-1);
            }
        }
        System.out.println(road[k]);
    }
    static int n;
}