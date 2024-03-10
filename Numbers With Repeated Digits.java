class Solution {
    int dp[][][];
    public int numDupDigitsAtMostN(int n) {
        String s = "" + n;
        int len = s.length();
        int ans = 0;
        int[] f = new int[10];
        f[1] = 9;
        int mul = 9;
        for(int i = 2; i < 10; i++) {
            f[i] = f[i - 1] * mul;
            mul--;
        }
        for(int i = 1; i < len; i++) {
            ans += f[i];
        }
        
        dp = new int[s.length() + 1][2][(1 << 11)];
        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[0].length; j++) {
                for(int k = 0; k < dp[0][0].length; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        
        for(int d = 1; d <= (s.charAt(0) - '0'); d++) {
            if(d == (s.charAt(0) - '0')) {
                ans += dfs(s, 1, 1, 1 << d);
            } else {
                ans += dfs(s, 1, 0, 1 << d);
            }
        }
        return n - ans;
    }
    
    public int dfs(String s, int i, int same, int bit) {
        if(i >= s.length()) {
            return 1;
        }
        
        if(dp[i][same][bit] != -1) {
            return dp[i][same][bit];
        }
        
        int count = 0;
        int d = s.charAt(i) - '0';
        if(same == 0) { //not the same prefix : [0 : 9]
            for(int x = 0; x <= 9; x++) {
                if((bit & (1 << x)) == 0) {
                    count += dfs(s, i + 1, 0, bit ^ (1 << x));
                }
            }
        } else { //same prefix : [0 : d]
            for(int x = 0; x < d; x++) {
                if((bit & (1 << x)) == 0) {
                    count += dfs(s, i + 1, 0, bit ^ (1 << x));
                }
            }
            if((bit & (1 << d)) == 0) {
                count += dfs(s, i + 1, 1, bit ^ (1 << d));
            }
        }
        
        return dp[i][same][bit] = count;
    }
}
