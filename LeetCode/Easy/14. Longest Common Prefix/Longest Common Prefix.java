class Solution {

    public static String longestCommonPrefix(String[] strs) {

        Trie trie = new Trie();

        for (String s : strs) {
            if (s.equals("")) return ""; // 예외처리
            trie.insert(s);
        }

        return trie.getAns();
    }
}

class Trie {

    private Node root;

    public Trie() { root = new Node(); }

    public void insert(String s) {

        Node curr = root;

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);
            int idx = c - 'a';

            if (curr.children[idx]==null) {
                curr.children[idx] = new Node();
                curr.cnt++;
            }

            curr = curr.children[idx];
        }
        
        curr.isEnd = true;
    }

    public String getAns() {

        StringBuilder ans = new StringBuilder();

        Node curr = root;

        while (curr.cnt==1) {   // 분기되는 지점부터 common prefix 붕괴

            for (int i = 0; i < 26; i++){
                if (curr.children[i]!=null) {
                    ans.append((char)('a'+i));  // 문자 추가 후, 자식으로 이동
                    curr = curr.children[i];
                    break;
                }
            }
            if (curr.isEnd) return ans.toString();
        }

        return ans.toString();
    }

}

class Node {

    Node[] children;
    int cnt;    // 자식 갯수로 중단 조건
    boolean isEnd;

    public Node() {
        this.children = new Node[26];
        this.cnt = 0;
        this.isEnd = false;
    }
}