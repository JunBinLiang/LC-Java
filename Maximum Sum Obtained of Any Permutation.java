class Solution {
    int MOD = 1000000007;
    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        int[] diff = new int[nums.length];
        for(int[] p : requests) {
            diff[p[0]]++;
            if(p[1] + 1 < diff.length) {
                diff[p[1] + 1]--;
            }
        }
        
        int[] count = new int[nums.length];
        int s = 0;
        for(int i = 0; i < count.length; i++) {
            s += diff[i];
            count[i] = s;
        }
        
        Arrays.sort(count);
        Arrays.sort(nums);
        long ans = 0;
        for(int i = 0; i < count.length; i++) {
            ans += ((count[i] + 0l) * nums[i]);
            ans %= MOD;
        }
        return (int)ans;
    }
}
