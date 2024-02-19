/*
class Solution {
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        
        int[][] dp = new int[n][2]; //ending with nums[i], in / de
        int ans = 0;
        for(int i = 0; i < n; i++) {
            dp[i][0] = dp[i][1] = 1;
            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j]) {
                    dp[i][0] = Math.max(dp[i][0], 1 + dp[j][1]);
                } else if(nums[i] < nums[j]) {
                    dp[i][1] = Math.max(dp[i][1], 1 + dp[j][0]);
                }
            }
            ans = Math.max(ans, Math.max(dp[i][0], dp[i][1]));
        }
        
        return ans;
    }
}
*/

public class Solution {
    public int wiggleMaxLength(int[] nums) {
        
        if( nums.length == 0 ) return 0;
        
        int[] up = new int[nums.length];
        int[] down = new int[nums.length];
        
        up[0] = 1;
        down[0] = 1;
        
        for(int i = 1 ; i < nums.length; i++){
            if( nums[i] > nums[i-1] ){
                up[i] = down[i-1]+1;
                down[i] = down[i-1];
            }else if( nums[i] < nums[i-1]){
                down[i] = up[i-1]+1;
                up[i] = up[i-1];
            }else{
                down[i] = down[i-1];
                up[i] = up[i-1];
            }
        }
        
        return Math.max(down[nums.length-1],up[nums.length-1]);
    }
}
