using ll = long long;
class Solution {
public:
    long long maximumStrength(vector<int>& a, int k) {
        reverse(a.begin(), a.end());
        int n = a.size();
        vector<vector<vector<ll>>> dp(n, vector<vector<ll>>(k + 1, vector<ll>(2, -1e18)));
        dp[0][0][0] = 0;
        dp[0][1][1] = a[0];
        ll ans = -1e18;
        for(int i = 1; i < n; i++) {
            dp[i][0][0] = 0;
            for(int j = 1; j <= min(k, i + 1); j++) {
                dp[i][j][1] = max(dp[i - 1][j][1] + (a[i] + 0ll) * get(j), dp[i - 1][j - 1][0] + (a[i] + 0ll) * get(j));
                dp[i][j][1] = max(dp[i][j][1], dp[i - 1][j - 1][1] + (a[i] + 0ll) * get(j));
                dp[i][j][0] = max(dp[i - 1][j][0], dp[i - 1][j][1]);
            }
        }
        
        for(int i = 0; i < n; i++) {
            ans = max(ans, dp[i][k][0]);
            ans = max(ans, dp[i][k][1]);
        }
        return ans;
    }
    
    int get(int i) {
        if(i % 2 == 1) return i;
        else return -i;
    }
};
