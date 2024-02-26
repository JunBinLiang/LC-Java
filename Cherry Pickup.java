class Solution {
    public int cherryPickup(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        int[][][] dp = new int[n + 1][m + 1][n + 1]; //[r1, c1, r2]
        
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= m; j++) {
                for(int k = 0; k <= n; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        
        if(mat[0][0] == 1) {
            dp[0][0][0] = 1;
        } else {
            dp[0][0][0] = 0;
        }
        
        
        for(int r1 = 0; r1 < n; r1++) {
            for(int c1 = 0; c1 < m; c1++) {
                for(int r2 = 0; r2 < n; r2++) {
                    //current position => r1, c1, r2
                    //(r1 + 1, c1)   (r2 + 1, c2)
                    //(r1, c1 + 1)   (r2 + 1, c2)
                    //(r1 + 1, c1)   (r2, c2 + 1)
                    //(r1, c1 + 1)   (r2, c2 + 1)
                    int c2 = r1 + c1 - r2;
                    if(c2 < 0 || dp[r1][c1][r2] == -1) continue;
                    
                    if(r1 + 1 < n && c1 < m && r2 + 1 < n && c2 < m && mat[r1 + 1][c1] != -1 && mat[r2 + 1][c2] != -1) {
                        int add = mat[r1 + 1][c1] + mat[r2 + 1][c2];
                        if(r1 + 1 == r2 + 1 && c1 == c2) add -=  mat[r1 + 1][c1];
                        dp[r1 + 1][c1][r2 + 1] = Math.max(dp[r1 + 1][c1][r2 + 1], dp[r1][c1][r2] + add);
                    }
                    
                    if(r1 < n && c1 + 1 < m && r2 + 1 < n && c2 < m && mat[r1][c1 + 1] != -1 && mat[r2 + 1][c2] != -1) {
                        int add = mat[r1][c1 + 1] + mat[r2 + 1][c2];
                        if(r1 == r2 + 1 && c1 + 1 == c2) add -=  mat[r1][c1 + 1];
                        dp[r1][c1 + 1][r2 + 1] = Math.max(dp[r1][c1 + 1][r2 + 1], dp[r1][c1][r2] + add);
                    }
                    
                    if(r1 + 1 < n && c1 < m && r2 < n && c2 + 1 < m && mat[r1 + 1][c1] != -1 && mat[r2][c2 + 1] != -1) {
                        int add = mat[r1 + 1][c1] + mat[r2][c2 + 1];
                        if(r1 + 1 == r2 && c1 == c2 + 1) add -=  mat[r1 + 1][c1];
                        dp[r1 + 1][c1][r2] = Math.max( dp[r1 + 1][c1][r2], dp[r1][c1][r2] + add);
                    }
                    
                    if(r1 < n && c1 + 1 < m && r2 < n && c2 + 1 < m && mat[r1][c1 + 1] != -1 && mat[r2][c2 + 1] != -1) {
                        int add = mat[r1][c1 + 1] + mat[r2][c2 + 1];
                        if(r1 == r2 && c1 + 1 == c2 + 1) add -= mat[r1][c1 + 1];
                        dp[r1][c1 + 1][r2] = Math.max(dp[r1][c1 + 1][r2], dp[r1][c1][r2] + add);
                    }
                }
            }
        }
        
        return dp[n - 1][m - 1][n - 1] == -1 ? 0 : dp[n - 1][m - 1][n - 1];
    }
}
