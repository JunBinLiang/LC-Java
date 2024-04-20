/*
vector<int> d(n, INF);
d[s] = 0;
deque<int> q;
q.push_front(s);
while (!q.empty()) {
    int v = q.front();
    q.pop_front();
    for (auto edge : adj[v]) {
        int u = edge.first;
        int w = edge.second;
        if (d[v] + w < d[u]) {
            d[u] = d[v] + w;
            if (w == 1)
                q.push_back(u);
            else
                q.push_front(u);
        }
    }
}
*/

class Solution {
    final int INF = 1000000000;
    int dir[][] = new int[][]{{-1, -1}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int minCost(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[][][] d = new int[n][m][5]; //0: no change, original direction
                                        //1 - 4 change
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                for(int k = 0; k < 5; k++) {
                    d[i][j][k] = INF;
                }
            }
        }
        
        d[0][0][0] = 0;
        LinkedList<int[]> deq = new LinkedList<>();
        deq.add(new int[]{0, 0, 0});
        while(deq.size() > 0) {
            int p[] = deq.getFirst();
            deq.removeFirst();
            int r = p[0], c = p[1], st = p[2];
            if(st == 0) { //can change
                int nxtr = r + dir[grid[r][c]][0], nxtc = c + dir[grid[r][c]][1];
                if(inrange(n, m, nxtr, nxtc) && d[r][c][st] < d[nxtr][nxtc][0]) {
                    d[nxtr][nxtc][0] = d[r][c][st];
                    deq.addFirst(new int[]{nxtr, nxtc, 0});
                }
                
                for(int i = 1; i <= 4; i++) {
                    if(d[r][c][0] + 1 < d[r][c][i]) {
                        d[r][c][i] = d[r][c][0] + 1;
                        deq.addLast(new int[]{r, c, i});
                    }
                }
            } else { //can not change
                int nxtr = r + dir[st][0], nxtc = c + dir[st][1];
                if(inrange(n, m, nxtr, nxtc) && d[r][c][st] < d[nxtr][nxtc][0]) {
                    d[nxtr][nxtc][0] = d[r][c][st];
                    deq.addFirst(new int[]{nxtr, nxtc, 0});
                }
            }
        }
        
        int ans = INF;
        for(int i = 0; i < 5; i++) ans = Math.min(ans, d[n - 1][m - 1][i]);
        return ans;
    }
    
    public boolean inrange(int n, int m, int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }
}
