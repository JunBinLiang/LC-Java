class Solution {
    public int countDigitOne(int n) {
        String s = "" + n;
        int ans = 0;
        for(int len = 1; len < s.length(); len++) {
            int[][] dp1 = new int[len + 1][11];
            for(int[] p : dp1) Arrays.fill(p, -1);
            for(int d = 1; d <= 9; d++) {
                if(d == 1) ans += dfs1(dp1, len, 1, 1);
                else ans += dfs1(dp1, len, 1, 0);
            }
        }
        
        int[][][] dp2 = new int[s.length() + 1][2][11];
        for(int i = 0; i < dp2.length; i++) {
            for(int j = 0; j < dp2[0].length; j++) {
                for(int k = 0; k < dp2[0][0].length; k++) {
                    dp2[i][j][k] = -1;
                }
            }
        }
        
        for(int d = 1; d <= s.charAt(0) - '0'; d++) {
            int count = 0;
            if(d == 1) count = 1;
            if(d == s.charAt(0) - '0') ans += dfs2(dp2, s, 1, 1, count);
            else ans += dfs2(dp2, s, 1, 0, count);
        }
        return ans;
    }
    
    //处理length 跟n 长度不一样的情况
    public int dfs1(int[][] dp1, int len, int i, int count) {
        if(i >= len) {
            return count;
        }
        if(dp1[i][count] != -1) {
            return dp1[i][count];
        }
        int ans = 0;
        for(int d = 0; d <= 9; d++) {
            if(d == 1) {
                ans += dfs1(dp1, len, i + 1, count + 1);
            } else {
                ans += dfs1(dp1, len, i + 1, count);
            }
        }
        return dp1[i][count] = ans;
    }
    
    //处理length 跟n 长度一样的情况
    public int dfs2(int[][][] dp2, String s, int i, int same, int count) {
        if(i >= s.length()) {
            return count;
        }
        
        if(dp2[i][same][count] != -1) {
            return dp2[i][same][count];
        }
        
        int d = s.charAt(i) - '0';
        int ans = 0;
        if(same == 0) {
            for(int x = 0; x <= 9; x++) {
                if(x == 1) ans += dfs2(dp2, s, i + 1, 0, count + 1);
                else ans += dfs2(dp2, s, i + 1, 0, count);
            }
        } else {
            for(int x = 0; x < d; x++) {
                if(x == 1) ans += dfs2(dp2, s, i + 1, 0, count + 1);
                else ans += dfs2(dp2, s, i + 1, 0, count);
            }
            
            if(d == 1) {
                ans += dfs2(dp2, s, i + 1, 1, count + 1);
            } else {
                ans += dfs2(dp2, s, i + 1, 1, count);
            }
        }
        
        return dp2[i][same][count] = ans;
    }
}
