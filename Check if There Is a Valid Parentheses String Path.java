class Solution {
    public boolean hasValidPath(char[][] mat) {
        if(mat[0][0] == ')') return false;
        int n = mat.length, m = mat[0].length;
        boolean[][][] dp = new boolean[n][m][n + m + 5];
        dp[0][0][1] = true;
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(i == 0 && j == 0) continue;
                for(int k = 0; k < n + m; k++) { // 上一行多少个开
                    if(i - 1 >= 0) {
                        if(mat[i][j] == '(') {
                            dp[i][j][k + 1] |= dp[i - 1][j][k];
                        } else {
                            if(k > 0) dp[i][j][k - 1] |= dp[i - 1][j][k];
                        }
                    }
                    if(j - 1 >= 0) {
                        if(mat[i][j] == '(') {
                            dp[i][j][k + 1] |= dp[i][j - 1][k];
                        } else {
                            if(k > 0) dp[i][j][k - 1] |= dp[i][j - 1][k];
                        }
                    }
                }
            }
        }
        return dp[n - 1][m - 1][0];
    }
}
