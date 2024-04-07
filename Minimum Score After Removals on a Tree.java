class Solution {
    List<Integer> g[];
    int[] xor;
    boolean[] vis;
    int ans = 1000000000;
    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        xor = new int[n];
        vis = new boolean[n];
        g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        
        for(int i = 0; i < edges.length; i++) {
            for(int j = 0; j < n; j++) g[j] = new ArrayList<>();
            for(int j = 0; j < edges.length; j++) {
                if(i != j) {
                    g[edges[j][0]].add(edges[j][1]);
                    g[edges[j][1]].add(edges[j][0]);
                }
            }
            
            Arrays.fill(vis, false);
            int root1 = -1, root2 = -1;
            for(int j = 0; j < n; j++) {
                if(!vis[j]) { //root
                    dfs(nums, -1, j);
                    if(root1 == -1) root1 = j;
                    else root2 = j;
                }
            }
            dfs2(-1, root1, xor[root1], xor[root2]);
            dfs2(-1, root2, xor[root2], xor[root1]);
        }
        
        return ans;
    }
    
    public int dfs(int[] nums, int pa, int u) {
        vis[u] = true;
        xor[u] = nums[u];
        for(int v : g[u]) {
            if(v == pa) continue;
            xor[u] ^= dfs(nums, u, v);
        }
        return xor[u];
    }
    
    public void dfs2(int pa, int u, int xor1, int xor2) {
        for(int v : g[u]) {
            if(v == pa) continue;
            int x = xor[v];
            int y = xor1 ^ x;
            int z = xor2;
            int[] a = new int[]{x, y, z};
            Arrays.sort(a);
            ans = Math.min(ans, a[2] - a[0]);
            dfs2(u, v, xor1, xor2);
        }
    }
}
