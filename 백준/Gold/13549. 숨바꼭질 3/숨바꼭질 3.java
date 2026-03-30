import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] road = new int[100001];
        Arrays.fill(road, Integer.MAX_VALUE);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        road[n] = 0; pq.offer(n);

        while (!pq.isEmpty()) {

            int curr = pq.poll();

            if (curr+1 <= 100000 && road[curr+1] > road[curr]+1) {  // +1 logic
                road[curr+1] = road[curr]+1;
                pq.offer(curr+1);
            }
            if (curr-1 >= 0 && road[curr-1] > road[curr]+1) {   // -1 logic
                road[curr-1] = road[curr]+1;
                pq.offer(curr-1);
            }
            if (curr>0 && curr*2 <= 100000 && road[curr*2] > road[curr]) {  // *2 logic
                road[curr*2] = road[curr];
                pq.offer(curr*2);
            }
        }
        System.out.println(road[k]);
    }
    static int n;
}