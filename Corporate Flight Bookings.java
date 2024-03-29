class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] diff = new int[n + 1];
        for(int[] p : bookings) {
            p[0]--;
            p[1]--;
            diff[p[0]] += p[2];
            diff[p[1] + 1] -= p[2];
        }
        
        int[] ans = new int[n];
        int s = 0;
        for(int i = 0; i < n; i++) {
            s += diff[i];
            ans[i] = s;
        }
        return ans;
    }
}

// _    _    _    _    _
// 10      -10
//      20       -20
//      25                |-25
//10    55  45     25   25
