class Solution {
    int n, m, in, ex;
    int[] p;
    int[][][][] dp;
    public int getMaxGridHappiness(int n, int m, int in, int ex) {
        this.n = n;
        this.m = m;
        this.in = in;
        this.ex = ex;
        p = new int[10];
        p[0] = 1;
        for(int i = 1; i < 10; i++) p[i] = p[i - 1] * 3;
        
        dp = new int[n + 1][p[m] + 1][in + 1][ex + 1];
        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[0].length; j++) {
                for(int k = 0; k < dp[0][0].length; k++) {
                   for(int x = 0; x < dp[0][0][0].length; x++) {
                       dp[i][j][k][x] = -1;
                   }
                }
            }
        }
        return dfs(0, 0, in, ex);
    }
    
    public int dfs(int i, int prest, int a, int b) {
        if(i >= n) {
            return 0;
        }
        
        if(dp[i][prest][a][b] != -1) {
            return dp[i][prest][a][b];
        }
        
        int ans = 0;
        for(int curst = 0; curst < p[m]; curst++) {
            int[] count = cal(curst);
            if(count[0] <= a && count[1] <= b) {
                ans = Math.max(ans, getscore(prest, curst) + dfs(i + 1, curst, a - count[0], b - count[1]));
            } 
        }
        return dp[i][prest][a][b] = ans;
    }
    
    public int[] cal(int st) {
        int[] ans = new int[2];
        for(int i = 0; i < m; i++) {
            int x = st % 3;
            if(x == 1) ans[0]++;
            if(x == 2) ans[1]++;
            st /= 3;
        }
        return ans;
    }
    
    
    public int getscore(int prest, int curst) {
        int ans = 0;
        int[] a = new int[m];
        int[] b = new int[m];
        for(int i = 0; i < m; i++) {
            a[i] = prest % 3;
            prest /= 3;
        }
        
        for(int i = 0; i < m; i++) {
            b[i] = curst % 3;
            curst /= 3;
        }
        
        for(int i = 0; i < m; i++) {
            if(b[i] == 1) {
                ans += 120;
                if(i - 1 >= 0 && b[i - 1] != 0) {
                    ans -= 30;
                    if(b[i - 1] == 1) ans -= 30;
                    else ans += 20;
                }
                if(a[i] != 0) {
                    ans -= 30;
                    if(a[i] == 1) ans -= 30;
                    else ans += 20; 
                }
            }
            else if(b[i] == 2) {
                ans += 40;
                if(i - 1 >= 0 && b[i - 1] != 0) {
                    ans += 20;
                    if(b[i - 1] == 1) ans -= 30;
                    else ans += 20;
                }
                if(a[i] != 0) {
                    ans += 20;
                    if(a[i] == 1) ans -= 30;
                    else ans += 20; 
                }
            }
        }
        return ans;
    }
}
