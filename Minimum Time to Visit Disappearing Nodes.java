class Solution {
    public int[] minimumTime(int n, int[][] edges, int[] disappear) {
        int[] ans = new int[n];
        
        List<int[]>[] g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for(int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            g[u].add(new int[]{v, w});
            g[v].add(new int[]{u, w});
        }
        
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> {
            return x[1] - y[1];
        });
        boolean[] vis = new boolean[n];
        int[] d = new int[n];
        Arrays.fill(d, 1000000000);       
        d[0] = 0;
        pq.add(new int[]{0, 0});
        while(pq.size() > 0) {
            int[] p = pq.poll();
            int u = p[0];
            if(vis[u]) continue;
            vis[u] = true;
            for(int[] nxt : g[u]) {
                int v = nxt[0], w = nxt[1];
                if(d[u] + w < d[v] && d[u] + w < disappear[v]) {
                    d[v] = d[u] + w;
                    pq.add(new int[]{v, d[v]});
                }
            }
        }
        
        for(int i = 0; i < n; i++) {
            if(!vis[i]) ans[i] = -1;
            else ans[i] = d[i];
        }
        return ans;
    }
}
