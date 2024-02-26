class Solution {
    public int maxProductPath(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        long[][][] dp = new long[n][m][2]; //[+, -]
        long INF = Long.MAX_VALUE;
        long NINF = Long.MIN_VALUE;
        
        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[0].length; j++) {
                dp[i][j][0] = NINF;
                dp[i][j][1] = INF;
            }
        }
        
        boolean zero = false;
        
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                int x = mat[i][j];
                if(x == 0) {
                    zero = true;
                    continue;
                }
                  
                if(i - 1 >= 0) {
                    if(dp[i - 1][j][0] != NINF) { //+
                        if(x < 0) { //-
                            dp[i][j][1] = Math.min(dp[i][j][1],  x * dp[i - 1][j][0]);
                        } else { //+
                            dp[i][j][0] = Math.max(dp[i][j][0],  x * dp[i - 1][j][0]);
                        }
                    }
                    if(dp[i - 1][j][1] != INF) { //-
                        if(x < 0) { //-
                            dp[i][j][0] = Math.max(dp[i][j][0],  x * dp[i - 1][j][1]);
                        } else { //+
                            dp[i][j][1] = Math.min(dp[i][j][1],  x * dp[i - 1][j][1]);
                        }
                    }
                    
                }
                
                
                if(j - 1 >= 0) {
                    if(dp[i][j - 1][0] != NINF) { //+
                        if(x < 0) { //-
                            dp[i][j][1] = Math.min(dp[i][j][1],  x * dp[i][j - 1][0]);
                        } else { //+
                            dp[i][j][0] = Math.max(dp[i][j][0],  x * dp[i][j - 1][0]);
                        }
                    }
                    if(dp[i][j - 1][1] != INF) { //-
                        if(x < 0) { //-
                            dp[i][j][0] = Math.max(dp[i][j][0],  x * dp[i][j - 1][1]);
                        } else { //+
                            dp[i][j][1] = Math.min(dp[i][j][1],  x * dp[i][j - 1][1]);
                        }
                    }
                }

                if(i == 0 && j == 0) {
                    if(x < 0) dp[i][j][1] = x;
                    else dp[i][j][0] = x;
                }
            }
        }
        
        /*for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                System.out.println(dp[i][j][0] + " " + dp[i][j][1]);
            } System.out.println("__________");
        }*/
        
        if(zero) {
            return dp[n - 1][m - 1][0] == NINF ? 0 : (int)((dp[n - 1][m - 1][0]) % 1000000007);
        } else {
            return dp[n - 1][m - 1][0] == NINF ? -1 : (int)((dp[n - 1][m - 1][0]) % 1000000007);
        }
    }

}
