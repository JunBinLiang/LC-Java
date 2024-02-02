class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        for(int s = 0; s < (1 << n); s++) {
            List<Integer> subset = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                if((s & (1 << i)) > 0) { //bit is 1
                    subset.add(nums[i]);
                }
            }
            ans.add(subset);
        }
        return ans;
    }
}
