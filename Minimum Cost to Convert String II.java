class Node {
    Node[] next;
    boolean isLeaf = false;
    int id = -1;
    public Node() {
        next = new Node[26];
    }
}


class Solution {
    final long INF = 1000000000000000000l;
    Node root;
    public long minimumCost(String s, String t, String[] original, String[] changed, int[] cost) {
        root = new Node();
        Map<String, Integer> f = new HashMap<>();
        int id = 0; 
        for(int i = 0; i < original.length; i++) {
            String str = original[i];
            StringBuilder rev = new StringBuilder(str);
            str = rev.reverse().toString();
            if(!f.containsKey(str)) {
                f.put(str, id++);
            }
            original[i] = str;
        }
        
        for(int i = 0; i < changed.length; i++) {
            String str = changed[i];
            StringBuilder rev = new StringBuilder(str);
            str = rev.reverse().toString();
            if(!f.containsKey(str)) {
                f.put(str, id++);
            }
            changed[i] = str;
        }
        
        for(String key : f.keySet()) {
            add(key, f.get(key));
        }
        
        
        //floyd warshall algorithm
        long[][] d = new long[f.size()][f.size()];
        int sz = f.size();
        for(int i = 0; i < sz; i++) {
            for(int j = 0; j < sz; j++) {
                d[i][j] = INF;
            }
        }
        for(int i = 0; i < sz; i++) {
            d[i][i] = 0;
        }
        
        for(int i = 0; i < original.length; i++) {
            int c1 = f.get(original[i]), c2 = f.get(changed[i]);
            d[c1][c2] = Math.min(d[c1][c2], cost[i] + 0l);
        }
        
        for (int k = 0; k < sz; k ++ )
                    for (int i = 0; i < sz; i ++ )
                        for (int j = 0; j < sz; j ++ )
                            d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
        ///////////////////////////////////////////////////////////////////
        int n = s.length();
        long[] dp = new long[n];
        Arrays.fill(dp, INF);
        for(int i = 0; i < n; i++) {
            Node u1 = root;
            Node u2 = root;
            for(int j = i; j >= 0; j--) {
                int c1 = s.charAt(j) - 'a', c2 = t.charAt(j) - 'a';
                u1 = u1.next[c1];
                u2 = u2.next[c2];
                if(u1 == null || u2 == null) break;
                if(u1.id != -1 && u2.id != -1) {
                    long w = d[u1.id][u2.id];
                    if(j == 0) dp[i] = Math.min(dp[i], w);
                    else dp[i] = Math.min(dp[i], w + dp[j - 1]);
                }
            }
            
            boolean same = true;
            for(int j = i; j >= 0; j--) {
                if(s.charAt(j) != t.charAt(j)) break;
                if(j == 0) dp[i] = Math.min(dp[i], 0);
                else dp[i] = Math.min(dp[i], 0 + dp[j - 1]);
            }
        }
        
        return dp[dp.length - 1] == INF ? -1 : dp[dp.length - 1];
    }
    
    public void add(String s, int id) {
        Node u = root;
        for(int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            if(u.next[c] == null) {
                u.next[c] = new Node();
            }
            u = u.next[c];
        }
        u.id = id;
    }
}

//1. 最短路建立转换的cost
//2. dp -> word break
//3. 检查能不能通过一个substring 转换到另一个substring的时候，我们要把substring 转换回graph里的node id
//4. 如果用map 转换回id，会O(n ^ 3), 这里我们用trie 转换
