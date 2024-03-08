class Solution {
    public int maxStudents(char[][] seats) {
        int n = seats.length, m = seats[0].length;
        int[][] dp = new int[n][(1 << m) + 1];
        for(int[] p : dp) Arrays.fill(p, -1);
        
        for(int st = 0; st < (1 << m); st++) {
            if(check(seats, 0, 0, st)) {
                dp[0][st] = Integer.bitCount(st);
            }
        }
        
        for(int i = 1; i < n; i++) {
            for(int prest = 0; prest < (1 << m); prest++) {
                for(int curst = 0; curst < (1 << m); curst++) {
                    if(check(seats, i, prest, curst) && dp[i - 1][prest] != -1) {
                        dp[i][curst] = Math.max(dp[i][curst], dp[i - 1][prest] + Integer.bitCount(curst));
                    }
                }
            }
        }
        
        int ans = 0;
        for(int st = 0; st < (1 << m); st++) {
            ans = Math.max(ans, dp[n - 1][st]);
        }
        return ans;
    }
    
    public boolean check(char[][] seats, int row, int lastst, int st) {
        int pre = -10;
        int m = seats[0].length;
        for(int i = 0; i < m; i++) {
            if((st & (1 << i)) > 0) {
                if(seats[row][i] == '#') return false;
                if(i - 1 == pre) return false;
                pre = i;
                if(i - 1 >= 0 && (lastst & (1 << (i - 1))) > 0) return false;
                if(i + 1 < m && (lastst & (1 << (i + 1))) > 0) return false;
            }
        }
        return true;
    }
}
