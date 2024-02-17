class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] p = new long[n];
        
        for(int i = 0; i < n; i++) {
            p[i] = nums[i];
            if(i > 0) p[i] += p[i - 1];
        }
        
        
        LinkedList<long[]> mono = new LinkedList<>();
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            while(mono.size() > 0 && p[i] - mono.getFirst()[0] >= k) {
                ans = Math.min(ans, i - (int)(mono.getFirst()[1]));
                mono.removeFirst();
            }
             
            while(mono.size() > 0 && p[i] <= mono.getLast()[0]) mono.removeLast();
            mono.add(new long[]{p[i], i});
            if(p[i] >= k) ans = Math.min(ans, i + 1);
        }
        
        return ans > n ? -1 : ans;
    }
}
