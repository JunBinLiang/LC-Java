class Solution {
    public int minMoves(int[] nums, int limit) {
        //0 1 2
        int n = nums.length;
        int ans = 2 * n;
        int[] f = new int[2 * limit + 1];
        
        int[] diff = new int[2 * limit + 1];
        for(int i = 0; i < n / 2; i++) {
            int min = Math.min(nums[i], nums[n - i - 1]);
            int max = Math.max(nums[i], nums[n - i - 1]);
            int L = min + 1, R = max + limit;
            diff[L]++;
            if(R + 1 < diff.length) {
                diff[R + 1]--;
            }
            f[nums[i] + nums[n - i - 1]]++;
        }
        
        int[] count = new int[2 * limit + 1];
        int s = 0;
        for(int i = 0; i < count.length; i++) {
            s += diff[i];
            count[i] = s;
        }
        
        
        for(int fixval = 2; fixval <= 2 * limit; fixval++) {
            int oneOperation = count[fixval];
            int sum = (n / 2 - oneOperation) * 2 + oneOperation;
            //去重: 某些可以通过0次操作达到
            sum -= f[fixval];
            ans = Math.min(ans, sum);
        }
        
        return ans;
    }
}
