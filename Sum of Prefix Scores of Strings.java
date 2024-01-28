class Node {
    Node next[];
    int cnt;
    public Node() {
        next = new Node[26];
        cnt = 0;
    }
}

class Solution {
    Node root;
    public int[] sumPrefixScores(String[] words) {
        root = new Node();
        for(String s : words) {
            add(s);
        }
        
        int[] res = new int[words.length];
        for(int i = 0; i < words.length; i++) {
            Node u = root;
            for(int j = 0; j < words[i].length(); j++) {
                char c = words[i].charAt(j);
                u = u.next[c - 'a'];
                res[i] += u.cnt;
            }
        }
        
        return res;
    }
    
    public void add(String s) {
        Node u = root;
        for(int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            if(u.next[c] == null) {
                u.next[c] = new Node();
            }
            u = u.next[c];
            u.cnt++;
        }
    }
}
