class Solution {
    int[] dp;
    public int maxScore(int[] nums) {
        int n = nums.length;
        int st = (1 << n) - 1;
        dp = new int[st + 1];
        Arrays.fill(dp, -1);
        return dfs(nums, st);
    }
    
    public int gcd(int a, int b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }
    
    public int dfs(int[] nums, int st) {
        if(st == 0) {
            return 0;
        }
        
        if(dp[st] != -1) {
            return dp[st];
        }
        int ith = (nums.length - Integer.bitCount(st)) / 2 + 1;
        
        int ans = 0;
        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                if(((st & (1 << i)) > 0) && ((st & (1 << j)) > 0)) {
                    int newst = st ^ (1 << i);
                    newst ^= (1 << j);
                    ans = Math.max(ans, ith * gcd(nums[i], nums[j]) + dfs(nums, newst));
                }
            }
        }
        return dp[st] = ans;
    }
}










class Solution {
    public int maxScore(int[] nums) {
        int n = nums.length;
        int[] dp = new int[(1 << n) + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
    
        for(int st = 1; st < (1 << n); st++) {
            int count = Integer.bitCount(st);
            if(count % 2 == 1) continue;
            for(int i = 0; i < n; i++) {
                for(int j = i + 1; j < n; j++) {
                    if(((st & (1 << i)) > 0) && ((st & (1 << j)) > 0)) {
                        int last = st ^ (1 << i);
                        last ^= (1 << j);
                        if(dp[last] != -1) {
                            dp[st] = Math.max(dp[st], dp[last] + gcd(nums[i], nums[j]) * (count / 2));
                        }
                            
                    }
                }
            }
        }
        return dp[(1 << n) - 1];
    }
    
    public int gcd(int a, int b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }
}
