import java.util.Stack;
import java.util.StringTokenizer;

class Solution {
    public static String solution(int n, int k, String[] cmd) {

        LinkedList table = new LinkedList();

        table.setting(n, k); // 표 구성

        for (String command : cmd) { // 명령별 함수 분리

            StringTokenizer st = new StringTokenizer(command);

            switch (st.nextToken()) {
                case "D":
                    int d = Integer.parseInt(st.nextToken());
                    table.D(d);
                    break;
                case "U":
                    int u = Integer.parseInt(st.nextToken());
                    table.U(u);
                    break;
                case "C":
                    table.C();
                    break;
                case "Z":
                    table.Z();
                    break;
            }
        }

        StringBuilder sb = new StringBuilder();
//        table.Rollback(); // 마지막 정답 구성을 위한 테스트
//        Node check = table.head.next;
        for (int i = 0; i < n; i++) {
            Node check = table.origin[i];
            if (check.delete) sb.append("X");
            else sb.append("O");
//            check = check.next;
        }

        return sb.toString();
    }
}

class LinkedList {

    Node head, tail, curr;
    Stack<Node> s; // Z: 후입선출이므로 스택 활용
    Node[] origin; // 출력 조회용 원본 배열

    public LinkedList() {
        this.head = new Node();
        this.tail = new Node();
        this.head.next = tail;
        this.tail.prev = head;
        this.s = new Stack<>();
    }

    public void setting(int n, int k) {

        origin = new Node[n];
        Node tmp = head;
        for (int i = 0; i < n; i++) { // n개 링크 형성
            Node gen = new Node();
            origin[i] = gen;
            gen.next = tmp.next; // gen부터 연결
            gen.prev = tmp;
            tmp.next.prev = gen; // 앞, 뒤 노드의 링크 재연결
            tmp.next = gen;
            tmp = tmp.next;
            if (i==k) curr = tmp; // k번째 가리키는 상태로 초기화
        }
    }

    public void U(int x) {
        for (int i = 0; i < x; i++) {
            if (curr.prev.equals(head)) break;
            curr = curr.prev;
        }
    }

    public void D(int x) {
        for (int i = 0; i < x; i++) {
            if (curr.next.equals(tail)) break;
            curr = curr.next;
        }
    }

    public void C() {

        curr.delete = true; // 플래그 on (배열 조회용)
        s.push(curr);
        curr.next.prev = curr.prev; //링크 끊기
        curr.prev.next = curr.next;
        if (curr.next.equals(tail)) curr = curr.prev;
        else curr = curr.next;
    }

    public void Z() {

        Node re = s.pop(); // 가장 마지막에 삭제된 노드 복구
        re.next.prev = re;
        re.prev.next = re;
        re.delete = false; // 플래그 off
    }

    public void Rollback() {

        while (!s.isEmpty()) { // 스택 내부 노드는 모두 플래그 on state
            Node re = s.pop();
            re.next.prev = re;
            re.prev.next = re;
        }
    }
}

class Node {
    Node prev, next;
    boolean delete;
}