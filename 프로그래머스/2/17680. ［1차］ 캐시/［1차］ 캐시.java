import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int ans = 0;

        Cache cache = new Cache();

        for (String s : cities) {

            String key = s.toLowerCase();
            Node curr = cache.info.computeIfAbsent(key, k -> new Node()); // 맵에 없는 경우 추가

            if (curr.prev == null) { // 캐시에 없는 상태
                cache.insert(curr);
                cache.info.put(key, curr);
                ans+=5;
                if (++cache.capacity > cacheSize) { // 용량 초과 경우 앞 하나 제거
                    cache.delete();
                    cache.capacity--;
                }
            } else {
                cache.update(curr); // 캐시 내부 LRU 업데이트
                ans++;
            }
        }

        return ans;
    }

}

class Cache {

    Node head = new Node(), tail = new Node();
    int capacity = 0;
    Map<String, Node> info = new HashMap<>();

    public Cache() {
        head.next = tail;
        tail.prev = head;
    }

    public void insert(Node v) {

        v.prev = tail.prev;
        v.next = tail;
        tail.prev.next = v;
        tail.prev = v;
    }

    public void delete() {

        Node target = head.next;
        target.next.prev = head;
        head.next = target.next;

        target.prev = null;
    }

    public void update(Node v) {

        v.next.prev=v.prev; // 현재 위치의 앞, 뒤 연결
        v.prev.next=v.next;

        insert(v); // 현재 노드를 마지막에 추가
    }
}

class Node {
    Node prev, next;
}