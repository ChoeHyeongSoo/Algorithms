class Trie {

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {

        TrieNode curr = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            int idx = ch - 'a';

            if (curr.children[idx]==null) curr.children[idx] = new TrieNode();

            curr = curr.children[idx];
        }

        curr.isEnd = true;
    }

    public boolean search(String word) {

        TrieNode curr = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            int idx = ch - 'a';

            if (curr.children[idx]==null) return false;

            curr = curr.children[idx];
        }

        return curr.isEnd; // 마지막 문자가 저장된 노드가 isEnd가 아니라면 없는 거!
    }

    public boolean startsWith(String prefix) {

        TrieNode curr = root;

        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);

            int idx = ch - 'a';

            if (curr.children[idx]==null) return false;

            curr = curr.children[idx];
        }

        return true; // 뒤에 더 있더라도 접두사로 시작하는 상황!
    }
}

class TrieNode {
    TrieNode[] children;
    boolean isEnd;

    public TrieNode() {
        this.children = new TrieNode[26];
        this.isEnd = false;
    }
}
/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */