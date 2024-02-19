class Solution {
    public int minSwap(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[][] dp = new int[n][2]; //0 no swap 1 swap
        
        int INF = 1000000000;
        for(int[] p : dp) Arrays.fill(p, INF);
        
        dp[0][0] = 0;
        dp[0][1] = 1;
        
        for(int i = 1; i < n; i++) {
            //non swap  dp[i][0]
            
            //previous nonswap
            if(nums1[i] > nums1[i - 1] && nums2[i] > nums2[i - 1]) {
                dp[i][0] = Math.min(dp[i][0], dp[i - 1][0] + 0);
            }
            //previous swap
            if(nums1[i] > nums2[i - 1] && nums2[i] > nums1[i - 1]) {
                dp[i][0] = Math.min(dp[i][0], dp[i - 1][1] + 0);
            }
            
            //swap  dp[i][1]
            //previous not swap
            if(nums2[i] > nums1[i - 1] && nums1[i] > nums2[i - 1]) {
                dp[i][1] = Math.min(dp[i][1], dp[i - 1][0] + 1);
            }
            
            //previous not swap
            if(nums2[i] > nums2[i - 1] && nums1[i] > nums1[i - 1]) {
                dp[i][1] = Math.min(dp[i][1], dp[i - 1][1] + 1);
            }
            
        }
        return Math.min(dp[n - 1][0], dp[n - 1][1]);
    }
}
