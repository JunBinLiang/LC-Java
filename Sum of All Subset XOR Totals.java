class Solution {
    public int subsetXORSum(int[] nums) {
        int ans = 0, n = nums.length;
        for(int s = 0; s < (1 << n); s++) {
            int xo = 0;
            for(int i = 0; i < n; i++) {
                if((s & (1 << i)) > 0) {
                    xo ^= nums[i];
                }
            }
            ans += xo;
        }
        return ans;
    }
}
