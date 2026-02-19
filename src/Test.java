import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

class Subject {

    int title;
    int pre_cnt;
    int[] pre;

    public Subject(int title, int pre_cnt, int[] pre) {
        super();
        this.title = title;
        this.pre_cnt = pre_cnt;
        this.pre = pre;
    }
}

public class Test {

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input_A1_Subject.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T;
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            int n = Integer.parseInt(br.readLine());
//            Subject[] subjects = new Subject[n+2];      // #1) 새로운 파일로 완전탐색: 모든 과목 돌면서 cnt 체크, 방문 체크, done 추가시 학기 증가, 아닐 시 탈출
//            Subject[][] Lec = new Subject[100][n+2];    // #2) L과 비교
            List<Subject>[] L = new ArrayList[n+2];     // 각 row는 필요한 선행과목 수, 학기별로 수강 가능 과목 최대여부를 위해서 생성
            int init_cnt = 0;   // L[0]==null 이면 동일한 의미

            for (int i = 1; i <= n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int cur_cnt = Integer.parseInt(st.nextToken());
                int[] pre = new int[cur_cnt];
                for (int p = 0; p < cur_cnt; p++)
                    pre[p] = Integer.parseInt(st.nextToken());
                Subject curr = new Subject(i, cur_cnt, pre);
//                subjects[i] = curr;
//                Lec[cur_cnt][i] = curr;

                if (L[cur_cnt]==null)
                    L[cur_cnt] = new ArrayList<>();
                L[cur_cnt].add(curr);

                if (cur_cnt==0) {   // L[0]이 null이면 init_cnt==0이라는 의미
                    init_cnt++;
                }
            }

            int[] complete = new int[n+2];          // boolean[] -> int[] 해당 학기 체크 위해서 - 현재 학기 보다 이전으로 기록된 건 이번 학기에 방문처리 가능
            boolean[] done_list = new boolean[n+2]; // 방문배열이나 map 등 사이클마다 변화 체크를 하는 경우, 전 사이클과 이번 사이클의 배열을 완전 분리하는 게 오류 감소


            Deque<Subject> dq = new LinkedList<>();
            int done = 0;

            for (int i = 0; i < init_cnt; i++) {
                done++;
                done_list[L[0].get(i).title] = true;    // complete[L[0].get(i).title] = 1;
            }

            int semester = -1;

            if (done > 0) {     // 0이 없는 경우 건너뛰기
                semester = 1;
                for (int i = 1; i <= done; i++) {   // 현재까지 수강한 과목 수만큼까지는 이번 학기에 수강 가능한 경우 존재
                    if (L[i]==null) continue;
                    for (int j = 0; j < L[i].size(); j++) {
                        dq.offer(L[i].get(j));
                    }
                }
            }

            semester = getSemester_Queue(dq, n, done_list, semester, done, L);  // 큐의 입력과 각 사이클 처리를 어떻게 할지 머리 아프면 큐를 안 쓰는 게 좋았다

            System.out.println("#" + tc + " " + semester);
        }

    }

    private static int getSemester_Queue(Deque<Subject> dq, int n, boolean[] done_list, int semester, int done, List<Subject>[] L) {

        // semester 처리 .. 다음 학기 semester와
        // 이수한 과목 수 추가 / 이번 사이클에 처리한 과목 수 = 0 탈출, 증가하면 증가한 만큼 추가
        int done_v = 0;   // 선행과목수가 done이하인 건 queue에 투입 - 이미 들어간 것과 새로 들어갈 것을 어떻게 구분할 것인가? 전부 빼고 방문처리 된 거 제외 다시 넣기?
        // 직전 사이클에 투입된 거 기록한 뒤 그 이후 것들 queue에 추가 -? done에 기록, done과 curr로 각 사이클마다 큐에 들어갈 것 구분

        outer:
        while (!dq.isEmpty()) {
            int curr = 0;                // 이번 사이클의 수강 완료 가능 수
            int[] curr_idx = new int[n]; // 사이클 종료 후 방문처리 하기 위한 인덱스 배열

            int loop_size = dq.size();   // #!) 아래 for문 dq.size()동안 루프했는데, poll하면 감소돼서 대기 중인 과목 처리x
            for (int cur_s = 0; cur_s < loop_size; cur_s++) {
                Subject tmp = dq.poll();
                if (tmp==null) continue; // addLast로 발생하는 null 처리용
                curr_idx[curr] = tmp.title;     // curr에 무조건 넣어두고, 수강 불가 시에 curr--를 통해서 다음에 덮어쓰기
                curr++; // complete[tmp.title] = semester+1;

                visit_loop:
                for (int v : tmp.pre) {
                    if(!done_list[v]){
                        dq.offer(tmp);  // complete[tmp.title] = 0;   <- 이번 사이클 방문과 구분하기 위해서 0이면 수강x, 각 정수는 수강한 학기 의미
                        curr--;               // curr_idx의 마지막에 값이 존재하고 끝나도 완료 처리할 때 인덱스로 제어
                        break visit_loop;
                    }
                }
            }

            if (curr==0) {      // 이번 사이클에서 완료 가능한 게 없으면 불가능-종료
                semester = -1;
                break outer;
            }

            for (int i = 0; i < curr; i++) {       // 이번 학기 끝 수료 처리
                done_list[curr_idx[i]] = true;
            }

            for (int cnt = done +1; cnt <= done +curr; cnt++) { // 다음학기 수강가능 과목 추가
                if (L[cnt]==null) continue;
                for (int i = 0; i < L[cnt].size(); i++)
                    dq.offer(L[cnt].get(i));
            }

            done +=curr;     // 다음 사이클에서 큐에 추가할 범위 (시작점) 미리 셋팅
            semester++;
        }

        return semester;
    }

}



/*
4
4
1 3
2 1 3
1 4
0
2
1 2
1 1
4
1 2
1 3
1 4
0
4
1 2
1 3
1 1
0
 */

