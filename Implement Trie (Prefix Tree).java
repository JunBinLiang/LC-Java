class Node {
    Node[] next;
    boolean isLeaf = false;
    public Node() {
        next = new Node[26];
    }
}


class Trie {
    Node root;
    public Trie() {
        root = new Node();
    }
    
    public void insert(String s) {
        Node u = root;
        for(int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            if(u.next[c] == null) {
                u.next[c] = new Node();
            }
            u = u.next[c];
        }
        u.isLeaf = true;
    }
    
    public boolean search(String s) {
        Node u = root;
        for(int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            if(u.next[c] == null) {
                return false;
            }
            u = u.next[c]; //not null
        }
        return u.isLeaf;
    }
    
    public boolean startsWith(String prefix) {
        Node u = root;
        for(int i = 0; i < prefix.length(); i++) {
            int c = prefix.charAt(i) - 'a';
            if(u.next[c] == null) {
                return false;
            }
            u = u.next[c]; //not null
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
