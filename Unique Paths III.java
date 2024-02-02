class Solution {
    int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    int nonobstacle = 0;
    public int uniquePathsIII(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        boolean[][] visit = new boolean[n][m];
        
        int si = -1, sj = -1;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 1) {
                    si = i;
                    sj = j;
                }
                if(grid[i][j] == 0) {
                    nonobstacle++;
                }
            }
        }
        
        visit[si][sj] = true;
        return dfs(grid, si, sj, visit, 0);
    }
    
    public int dfs(int[][] grid, int i, int j, boolean[][] visit, int cnt) {
        int n = grid.length, m = grid[0].length;
        if(grid[i][j] == 2) {
            if(cnt == nonobstacle) {
                return 1;
            }
            return 0;
        }

        int ans = 0;
        for(int x = 0; x < 4; x++) {
            int nr = i + dir[x][0], nc = j + dir[x][1];
            if(nr >= 0 && nr < n && nc >= 0 && nc < m && grid[nr][nc] != -1 && !visit[nr][nc]) {
                visit[nr][nc] = true;
                ans += dfs(grid, nr, nc, visit, cnt + ((grid[nr][nc] == 0) ? 1 : 0));
                visit[nr][nc] = false;
                
            }
        }
        return ans;
    }
}
