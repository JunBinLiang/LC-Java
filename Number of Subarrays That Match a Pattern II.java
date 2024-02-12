class Solution {
    long[] h;
    long[] p;
    int base = 101;
    int MOD = 1000000007;
    public int countMatchingSubarrays(int[] nums, int[] pattern) {
        int n = nums.length;
        h = new long[n + 5];
        p = new long[n + 5];
        
        //roing hash init
        p[0] = 1;
        for(int i = 1; i < p.length; i++) {
            p[i] = p[i - 1] * base;
            p[i] %= MOD;
        }
    
        
        StringBuilder str = new StringBuilder();
        for(int i = 1; i < nums.length; i++) {
            char c = ' ';
            if(nums[i] > nums[i - 1]) { //1 -> 2
                 c = '2';
            } else if(nums[i] == nums[i - 1]) { //0 -> 1
                c = '1';
            } else { //-1 -> 0
                c = '0';
            }
            str.append(c);
        }
        
        String s = str.toString();
        h[0] = s.charAt(0);
        for(int i = 1; i < s.length(); i++) {
            int c = s.charAt(i);
            h[i] = (h[i - 1] * base) + c;
            h[i] %= MOD;
        }
        
        
        long hash = 0;
        for(int i = 0; i < pattern.length; i++) {
            char c = ' ';
            if(pattern[i] == 1) c = '2';
            if(pattern[i] == 0) c = '1';
            if(pattern[i] == -1) c = '0';
            hash = hash * base + c;
            hash %= MOD;
        }
        
        int m = pattern.length;
        int ans = 0;
        for(int i = 0; i + m - 1 < s.length(); i++) {
            if(get(i, i + m - 1) == hash) {
                ans++;
            }
        }
        return ans;
    }
    
    public long get(int l, int r) {
        int sz = r - l + 1;
        if(l == 0) return h[r];
        return (h[r] - (h[l - 1] * p[sz] % MOD) + MOD) % MOD;
    }
}
