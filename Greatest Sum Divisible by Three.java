class Solution {
    public int maxSumDivThree(int[] nums) {
        
        int n = nums.length;
        int[][] dp = new int[n][3];
        for(int[] p : dp) Arrays.fill(p, -1);
        
        dp[0][nums[0] % 3] = nums[0];
        for(int i = 1; i < nums.length; i++) {
            for(int j = 0; j < 3; j++) {
                if(dp[i - 1][j] != -1) {
                    dp[i][(j + nums[i]) % 3] = Math.max(dp[i][(j + nums[i]) % 3], dp[i - 1][j] + nums[i]);
                }
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
            }
            dp[i][nums[i] % 3] = Math.max(dp[i][nums[i] % 3] , nums[i]);
        }
        
        return dp[n - 1][0] == -1 ? 0 : dp[n - 1][0];
    }
}
