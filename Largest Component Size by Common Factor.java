class Solution {
    public int find(int[] nums, int u) {
        return nums[u] == u ? u : 0 + (nums[u] = find(nums, nums[u]));
    }
    
    public int largestComponentSize(int[] a) {
        int maxv = 0;
        for(int i : a) maxv = Math.max(maxv, i);
        boolean[] vis = new boolean[maxv + 1];
        for(int i : a) vis[i] = true;
        
        int[] nums = new int[maxv + 1];
        for(int i = 0; i < nums.length; i++) nums[i] = i;
        
        for(int i = 2; i <= maxv; i++) {
            int p = -1;
            for(int j = i; j <= maxv; j += i) {
                if(vis[j]) {
                    if(p == -1) p = j;
                    else {
                        int r1 = find(nums, p); int r2 = find(nums, j);
                        if(r1 != r2) nums[r1] = r2;
                        p = j;
                    }
                }
            }
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for(int i : nums) {
            int r = find(nums, i);
            if(!map.containsKey(r)) {
                map.put(r, 1);
            } else {
                map.put(r, map.get(r) + 1);
            }
            ans = Math.max(ans, map.get(r));
        }
        
        return ans;
    }
}



/*
class Solution {
    public int find(int[] nums, int u) {
        return nums[u] == u ? u : 0 + (nums[u] = find(nums, nums[u]));
    }
    
    public int largestComponentSize(int[] a) {
        int maxv = 0;
        for(int i : a) maxv = Math.max(maxv, i);
        boolean[] vis = new boolean[maxv + 1];
        for(int i : a) vis[i] = true;
        
        int[] nums = new int[maxv + 1];
        int[] sz = new int[maxv + 1];
        for(int i = 0; i < nums.length; i++) {
            nums[i] = i;
            sz[i] = 1;
        }
        
        int ans = 1;
        for(int i = 2; i <= maxv; i++) {
            int p = -1;
            for(int j = i; j <= maxv; j += i) {
                if(vis[j]) {
                    if(p == -1) p = j;
                    else {
                        int r1 = find(nums, p); int r2 = find(nums, j);
                        if(r1 != r2) {
                            nums[r1] = r2;
                            sz[r2] += sz[r1];
                            ans = Math.max(ans, sz[r2]);
                        }
                        p = j;
                    }
                }
            }
        }
        
        return ans;
    }
}

*/
