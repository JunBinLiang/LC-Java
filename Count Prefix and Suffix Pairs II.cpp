using ll = long long;
int base = 26, MOD = 1e9 + 7;

class Solution {
public:
    long long countPrefixSuffixPairs(vector<string>& a) {
        
        ll ans = 0;
        int n = a.size();
        map<pair<ll, ll>, int> f;
        for(int j = n - 1; j >= 0; j--) {
            string s = a[j];
            int sz = s.size();
            ll h1 = 0, h2 = 0;
            for(int i = 0; i < sz; i++) {
                h1 = h1 * base + (s[i] - 'a' + 1);
                h2 = h2 * base + (s[sz - i - 1] - 'a' + 1);
                h1 %= MOD;
                h2 %= MOD;
            }
            if(f.find({h1, h2}) != f.end()) {
                ans += f[{h1, h2}];
            }
            
            h1 = 0; h2 = 0;
            for(int i = 0; i < sz; i++) {
                h1 = h1 * base + (s[i] - 'a' + 1);
                h2 = h2 * base + (s[sz - i - 1] - 'a' + 1);
                h1 %= MOD;
                h2 %= MOD;
                f[{h1, h2}]++;
            }
        }
        
        return ans;
    }
};
