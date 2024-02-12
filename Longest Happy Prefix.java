class Solution {
    long[] h;
    long[] p;
    int base = 101;
    int MOD = 1000000007;
    
    public long get(int l, int r) {
        int sz = r - l + 1;
        if(l == 0) return h[r];
        return (h[r] - (h[l - 1] * p[sz] % MOD) + MOD) % MOD;
    }
    
    public String longestPrefix(String s) {
        int n = s.length();
        
        h = new long[n + 5];
        p = new long[n + 5];
        
        //roing hash init
        p[0] = 1;
        for(int i = 1; i < p.length; i++) {
            p[i] = p[i - 1] * base;
            p[i] %= MOD;
        }
    
        h[0] = s.charAt(0) - 'a';
        for(int i = 1; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            h[i] = (h[i - 1] * base) + c;
            h[i] %= MOD;
        }
        ///////////////////////////////////////////////////////
        
        int ans = -1;
        for(int i = 0; i < n - 1; i++) {
            long pref = get(0, i), suff = get(n - i - 1, n - 1);
            if(pref == suff) {
                ans = i;
            }
        }
        
        return ans == -1 ? "" : s.substring(0, ans + 1);
    }
}
