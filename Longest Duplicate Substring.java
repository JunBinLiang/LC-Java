class Solution {
    long[][] h;
    long[][] p;
    int base1 = 7, base2 = 11;
    long MOD = 1000000007;
    
    public String longestDupSubstring(String s) {
        int n = s.length();
        
        h = new long[n + 5][2];
        p = new long[n + 5][2];
        
        //roing hash init
        p[0][0] = p[0][1] = 1;
        for(int i = 1; i < p.length; i++) {
            p[i][0] = p[i - 1][0] * base1;
            p[i][0] %= MOD;
            
            p[i][1] = p[i - 1][1] * base2;
            p[i][1] %= MOD;
        }
        
        h[0][0] = s.charAt(0) - 'a';
        h[0][1] = s.charAt(0) - 'a';
        for(int i = 1; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            h[i][0] = (h[i - 1][0] * base1) + c;
            h[i][0] %= MOD;
            
            h[i][1] = (h[i - 1][1] * base2) + c;
            h[i][1] %= MOD;
        }
        ///////////////////////////////////////////////////////
        int l = 1, r = n;
        int L = -1, R = -1;
        while(l <= r) {
            int mid = l + (r - l) / 2;
            boolean found = false;
            Map<String, Integer> map = new HashMap<>();
            for(int i = 0; i + mid - 1 < n; i++) {
                long hash1 = get(i, i + mid - 1, 0);
                long hash2 = get(i, i + mid - 1, 1);
                String key = hash1 + "|" + hash2;
                if(map.containsKey(key)) {
                    L = i; R = i + mid - 1;
                    found = true;
                    break;
                } else {
                    map.put(key, i);
                }
            }
        
            if(found) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        
        
        return L == -1 ? "" : s.substring(L, R + 1);
    }
    
    public long get(int l, int r, int i) {
        int sz = r - l + 1;
        if(l == 0) return h[r][i];
        return (h[r][i] - (h[l - 1][i] * p[sz][i] % MOD) + MOD) % MOD;
    }
}
