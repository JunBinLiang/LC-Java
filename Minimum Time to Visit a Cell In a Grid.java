class Solution {
    final int INF = 1000000000;
    int dir[][] = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int minimumTime(int[][] a) {
        int n = a.length, m = a[0].length;
        int[][] d = new int[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                d[i][j] = INF;
            }
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> {
            return x[2] - y[2];
        });
        
        if(a[0][1] <= 1) {
            d[0][1] = 1;
            pq.add(new int[]{0, 1, 1});
        }
        if(a[1][0] <= 1) {
            d[1][0] = 1;
            pq.add(new int[]{1, 0, 1});
        }
        
        boolean[][] vis = new boolean[n][m];
        while(pq.size() > 0) {
            int[] p = pq.poll();
            int r = p[0], c = p[1];
            if(vis[r][c]) continue;
            vis[r][c] = true;
           // System.out.println(Arrays.toString(p));
            for(int i = 0; i < 4; i++) {
                int nxtr = r + dir[i][0], nxtc = c + dir[i][1];
                if(inrange(n, m, nxtr, nxtc)) {
                    if(d[r][c] + 1 >= a[nxtr][nxtc] && d[r][c] + 1 < d[nxtr][nxtc]) {
                        d[nxtr][nxtc] = d[r][c] + 1;
                        pq.add(new int[]{nxtr, nxtc, d[nxtr][nxtc]});
                        //System.out.println("add1 " + nxtr + " " + nxtc + " " + d[nxtr][nxtc]);
                    }
                    if(d[r][c] + 1 < a[nxtr][nxtc]) {
                        int w = ((a[nxtr][nxtc] - d[r][c]) % 2 == 0) ? a[nxtr][nxtc] + 1 : a[nxtr][nxtc];
                        if(w < d[nxtr][nxtc]) {
                            d[nxtr][nxtc] = w;
                            pq.add(new int[]{nxtr, nxtc, d[nxtr][nxtc]});
                            //System.out.println("add2 " + nxtr + " " + nxtc + " " + d[nxtr][nxtc]);
                        }
                    }
                }
            }
        }
        return d[n - 1][m - 1] == INF ? -1 : d[n - 1][m - 1];
    }
    
    public boolean inrange(int n, int m, int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }
}
