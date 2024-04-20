class Solution {
    int dir[][] = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    final int INF = 1000000000;
    public int minimumObstacles(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[][] d = new int[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                d[i][j] = INF;
            }
        }
        d[0][0] = 0;
        LinkedList<int[]> deq = new LinkedList<>();
        deq.add(new int[]{0, 0});
        while(deq.size() > 0) {
            int[] p = deq.removeFirst();
            int r = p[0], c = p[1];
            for(int i = 0; i < 4; i++) {
                int nxtr = r + dir[i][0], nxtc = c + dir[i][1];
                if(inrange(n, m, nxtr, nxtc)) {
                    int w = grid[nxtr][nxtc];
                    if(d[r][c] + w < d[nxtr][nxtc]) {
                        d[nxtr][nxtc] = d[r][c] + w;
                        if(w == 0) deq.addFirst(new int[]{nxtr, nxtc});
                        else deq.addLast(new int[]{nxtr, nxtc});
                    }
                }
            }
        }
        return d[n - 1][m - 1];
    }
    
    public boolean inrange(int n, int m, int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }
}
