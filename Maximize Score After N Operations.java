class Solution {
    int[][] dp;
    public int maxScore(int[] nums) {
        int n = nums.length;
        int st = (1 << n) - 1;
        dp = new int[nums.length / 2 + 2][st + 1];
        for(int[] p : dp) Arrays.fill(p, -1);
        return dfs(nums, 1, st);
    }
    
    public int gcd(int a, int b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }
    
    public int dfs(int[] nums, int ith, int st) {
        if(st == 0) {
            return 0;
        }
        
        if(dp[ith][st] != -1) {
            return dp[ith][st];
        }
        
        int ans = 0;
        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                if(((st & (1 << i)) > 0) && ((st & (1 << j)) > 0)) {
                    int newst = st ^ (1 << i);
                    newst ^= (1 << j);
                    ans = Math.max(ans, ith * gcd(nums[i], nums[j]) + dfs(nums, ith + 1, newst));
                }
            }
        }
        return dp[ith][st] = ans;
    }
}
