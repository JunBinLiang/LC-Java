class Solution {
    long[] h;
    long[] p;
    int base = 101;
    int MOD = 1000000007;
    public int distinctEchoSubstrings(String s) {
        int n = s.length();
        
        h = new long[n + 5];
        p = new long[n + 5];
        
        //roing hash init
        p[0] = 1;
        for(int i = 1; i < p.length; i++) {
            p[i] = p[i - 1] * base;
            p[i] %= MOD;
        }
    
        h[0] = s.charAt(0) - 'a' + 1;
        for(int i = 1; i < s.length(); i++) {
            int c = s.charAt(i) - 'a' + 1;
            h[i] = (h[i - 1] * base) + c;
            h[i] %= MOD;
        }
        
        Set<Long> set = new HashSet<>();
        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                int sz = j - i + 1;
                //[i : j]  [j + 1, j + sz]
                if(j + sz < n && get(i, j) == get(j + 1, j + sz)) {
                    set.add(get(i, j));
                }
            }
        }
        return set.size();
    }
    
    public long get(int l, int r) {
        int sz = r - l + 1;
        if(l == 0) return h[r];
        return (h[r] - (h[l - 1] * p[sz] % MOD) + MOD) % MOD;
    }
}
