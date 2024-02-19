class Solution {
    public int checkRecord(int n) {
        
        long[][] dp = new long[n][6];
        //A
        //0 : 0 L
        //1 : 1 L
        //2 : 2 L
        
        //!A
        //3 : 0L
        //4 : 1L
        //5 : 2L
        
        int MOD = 1000000007;
        dp[0][3] = 1; //"p"
        dp[0][0] = 1;
        dp[0][4] = 1;
        
        for(int i = 1; i < n; i++) {
            //"A"
            dp[i][0] += dp[i - 1][3] + dp[i - 1][4] + dp[i - 1][5];
            dp[i][0] %= MOD;
            //"P"
            dp[i][0] += dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2];
            dp[i][0] %= MOD;
            
            dp[i][3] += dp[i - 1][3] + dp[i - 1][4] + dp[i - 1][5];
            dp[i][3] %= MOD;
            
            //"L"
            dp[i][1] += dp[i - 1][0];
            dp[i][1] %= MOD;
            dp[i][2] += dp[i - 1][1];
            dp[i][2] %= MOD;
            
            dp[i][4] += dp[i - 1][3];
            dp[i][4] %= MOD;
            dp[i][5] += dp[i - 1][4];
            dp[i][5] %= MOD;
        }
        
        long ans = 0;
        for(int i = 0; i < 6; i++) {
            ans += dp[n - 1][i];
            ans %= MOD;
        }
        return (int)(ans);
    }
}
