class Solution {
    public int find(int[] nums, int u) {
        return nums[u] == u ? u : 0 + (nums[u] = find(nums, nums[u]));
    }
    
    public int findCircleNum(int[][] isConnected) {
        
        int n = isConnected.length;
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = i;
        }
        
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                if(isConnected[i][j] == 1) {
                    int r1 = find(nums, i), r2 = find(nums, j);
                    if(r1 != r2) nums[r1] = r2;
                }
            }
        }
        
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < n; i++) {
            set.add(find(nums, i));
        }
        return set.size();
    }
}
